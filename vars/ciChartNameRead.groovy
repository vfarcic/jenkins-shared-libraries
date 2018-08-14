def call(project) {
    def chartName = "${project}-${env.BUILD_NUMBER}-${ciEscapeBranchName()}"
    return chartName
}