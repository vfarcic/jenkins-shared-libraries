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
    
    /* Download manifest-tool
    sh """bash -c "
        curl -L -o manifest-tool https://github.com/estesp/manifest-tool/releases/download/v0.7.0/manifest-tool-linux-amd64
        chmod +x manifest-tool"
    """
    
    // Create and push manifest list
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:${currentBuild.displayName}"
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:latest"
    */
  
    /** Manifest Create Version **/
    sh "docker manifest create dockerflow/${project}:${currentBuild.displayName} \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"

    // Manifest Annotate Version
    sh "docker manifest annotate dockerflow/${project}:${currentBuild.displayName} dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push Version
    sh "docker manifest push dockerflow/${project}:${currentBuild.displayName}"  
    
    /** Manifest Create LATEST **/
    sh "docker manifest create dockerflow/${project}:latest \
        dockerflow/${project}:${currentBuild.displayName}-linux-amd64 \
        dockerflow/${project}:${currentBuild.displayName}-linux-arm"

    // Manifest Annotate LATEST
    sh "docker manifest annotate dockerflow/${project}:latest dockerflow/${project}:${currentBuild.displayName}-linux-arm --os=linux --arch=arm --variant=v7"
  
    // Manifest Push LATEST
    sh "docker manifest push dockerflow/${project}:latest"

    dockerLogout()
}
