def call() {
    env.SHORT_GIT_COMMIT = "${env.GIT_COMMIT[0..10]}"
    env.BUILD_TAG = ciBuildVersionRead()

    echo "build tag set to: ${env.BUILD_TAG}"
}