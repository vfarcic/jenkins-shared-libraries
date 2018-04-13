def call(String project, gox = false) {
    /***************************************
     * Release images to docker hub
     ***************************************/
    dfLogin()
    
    // Push image for linux-amd64
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
    
    // Push image for linux-arm
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-arm"
    
    // Push image for docs latest
    sh "docker image push dockerflow/${project}-docs:latest"
    
    // Push image for docs
    sh "docker image push dockerflow/${project}-docs:${currentBuild.displayName}"
    
    dockerLogout()
}
