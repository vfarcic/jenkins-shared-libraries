def call(String project) {
    dockerLogin()
    sh "docker push vfarcic/${project}:latest"
    sh "docker push vfarcic/${project}:${currentBuild.displayName}"
    sh "docker push vfarcic/${project}-docs:latest"
    sh "docker push vfarcic/${project}-docs:${currentBuild.displayName}"
    dockerLogout()
}