def call() {
    env.shortGitCommit = "${env.GIT_COMMIT[0..10]}"
    env.BUILD_TAG = ciBuildVersionRead()

    echo "build tag set to: ${env.BUILD_TAG}"
}