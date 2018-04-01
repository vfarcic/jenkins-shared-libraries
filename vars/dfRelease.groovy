def call(String project, gox = false) {
    dockerLogin()
    sh "docker image push vfarcic/${project}:latest"
    sh "docker image push vfarcic/${project}:${currentBuild.displayName}"
    sh "docker image push vfarcic/${project}-docs:latest"
    sh "docker image push vfarcic/${project}-docs:${currentBuild.displayName}"
    dockerLogout()
}
