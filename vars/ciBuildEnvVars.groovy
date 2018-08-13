def call() {
    //https://issues.jenkins-ci.org/browse/JENKINS-52623 i found issue and waiting it to be fixed, i think carlos is on it now
    env.GIT_COMMIT = sh(script: """ git rev-parse HEAD """, returnStdout: true).trim()
    env.shortGitCommit = "${env.GIT_COMMIT[0..10]}"
    env.BUILD_TAG = ciBuildVersionRead()

    echo "build tag set to: ${env.BUILD_TAG}"
}