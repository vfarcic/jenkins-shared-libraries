def call(project, domain) {
    def addr = "undefined"
    if(env.BRANCH_NAME == 'master') {
        addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    } else if (env.BRANCH_NAME.toString().startsWith("hotfix")) {
        addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    } else  {
        addr = "${project}-${env.BRANCH_NAME}.${domain}"
    }

    return addr
}