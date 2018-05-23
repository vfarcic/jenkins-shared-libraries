/*******************************************
 * Release images to docker hub.
 *******************************************/
def call(String project, gox = false) {
  
    dfLogin()
    
    // Push image for linux-amd64
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
    
    // Push image for linux-arm
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-arm"
    
    // Push image for docs latest
    sh "docker image push dockerflow/${project}-docs:latest"
    
    // Push image for docs
    sh "docker image push dockerflow/${project}-docs:${currentBuild.displayName}"
  
    // Manifest Create dockerflow/${project}:${currentBuild.displayName}
    sh """docker manifest create dockerflow/${project}:${currentBuild.displayName} \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"""
  
    // Manifest Annotate dockerflow/${project}:${currentBuild.displayName}
    sh "docker manifest annotate dockerflow/${project}:${currentBuild.displayName} dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push dockerflow/${project}:${currentBuild.displayName}
    sh "docker manifest push dockerflow/${project}:${currentBuild.displayName}"  
    
  
    // Manifest Create dockerflow/${project}:latest
    sh """docker manifest create dockerflow/${project}:latest \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"""

    // Manifest Annotate dockerflow/${project}:latest
    sh "docker manifest annotate dockerflow/${project}:latest dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push dockerflow/${project}:latest
    sh "docker manifest push dockerflow/${project}:latest"

  
    // Manifest Create ${project}:${currentBuild.displayName}-linux-amd64
    sh """docker manifest create dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64"""
  
    // Manifest Push ${project}:${currentBuild.displayName}-linux-amd64
    sh "docker manifest push dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
  
  
    // Manifest Create and Push dockerflow/${project}:${currentBuild.displayName}-linux-arm
    sh """docker manifest create dockerflow/${project}:${currentBuild.displayName}-linux-arm \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"""
    
    // Manifest Annotate dockerflow/${project}:${currentBuild.displayName}-linux-arm
    sh "docker manifest annotate dockerflow/${project}:${currentBuild.displayName}-linux-arm dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest push dockerflow/${project}:${currentBuild.displayName}-linux-arm
    sh "docker manifest push dockerflow/${project}:${currentBuild.displayName}-linux-arm"
  
    dockerLogout()
}
