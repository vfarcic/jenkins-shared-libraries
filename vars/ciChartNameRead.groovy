def call(project) {
    def chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    return chartName
}