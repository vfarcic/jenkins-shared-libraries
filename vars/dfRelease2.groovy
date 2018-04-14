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
    
    // Download manifest-tool
    sh """bash -c "
        curl -L -o manifest-tool https://github.com/estesp/manifest-tool/releases/download/v0.7.0/manifest-tool-linux-amd64
        chmod +x manifest-tool"
    """
    
    // Create and push manifest list
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:${currentBuild.displayName}"
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:latest"
    
    dockerLogout()
}
