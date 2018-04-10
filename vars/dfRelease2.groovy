def call(String project, gox = false) {
    dfLogin()
    // Push image for linux-amd64
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
    
    sh "docker image push dockerflow/${project}-docs:latest"
    sh "docker image push dockerflow/${project}-docs:${currentBuild.displayName}"
    dockerLogout()
}
