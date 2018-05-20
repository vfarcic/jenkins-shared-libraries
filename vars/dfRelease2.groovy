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
      
    /** Manifest Create Version **/
    sh """sudo docker manifest create dockerflow/${project}:${currentBuild.displayName} \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"""

    // Manifest Annotate Version
    sh "sudo docker manifest annotate dockerflow/${project}:${currentBuild.displayName} dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push Version
    sh "docker manifest push dockerflow/${project}:${currentBuild.displayName}"  
    
    /** Manifest Create LATEST **/
    sh """sudo docker manifest create dockerflow/${project}:latest \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"""

    // Manifest Annotate LATEST
    sh "sudo docker manifest annotate dockerflow/${project}:latest dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push LATEST
    sh "docker manifest push dockerflow/${project}:latest"

    dockerLogout()
}
