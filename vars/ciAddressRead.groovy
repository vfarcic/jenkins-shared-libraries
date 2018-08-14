def call(project, domain) {
    def addr = "undefined"
    if(env.BRANCH_NAME == 'master') {
        addr = "${project}-${env.BUILD_NUMBER}-${ciEscapeBranchName()}.${domain}"
    } else if (env.BRANCH_NAME.toString().startsWith("hotfix")) {
        addr = "${project}-${env.BUILD_NUMBER}-${ciEscapeBranchName()}.${domain}"
    } else  {
        addr = "${project}-${ciEscapeBranchName()}.${domain}"
    }

    return addr
}