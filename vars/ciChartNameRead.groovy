def call(project) {

    def chartName = "should-be-replaced"

    if(ciCheckReleaseBranches()) {
        chartName = "${project}-${ciEscapeBranchName()}"
    } else {
        chartName = "${project}-${env.BUILD_NUMBER}-${ciEscapeBranchName()}"
    }

    return chartName
}