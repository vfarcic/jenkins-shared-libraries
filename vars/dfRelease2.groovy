def call(String project, gox = false) {
    dfLogin()
    sh "docker image push dockerflow/${project}:latest"
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}"
    sh "docker image push dockerflow/${project}-docs:latest"
    sh "docker image push dockerflow/${project}-docs:${currentBuild.displayName}"
    dockerLogout()
}
