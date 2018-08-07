def call(project, domain) {
    addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"

    return addr
}