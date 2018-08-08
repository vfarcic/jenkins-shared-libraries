def call(project, domain) {
    def addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"

    return addr
}