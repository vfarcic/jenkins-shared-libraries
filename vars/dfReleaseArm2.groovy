def call(String project) {
    // Login docker
    dfLogin()
    
    // Push image for linux-arm
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-arm"
    
    // Logout docker
    dockerLogout()
}
