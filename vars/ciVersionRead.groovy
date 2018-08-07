def call() {
    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")
    tag = env.BRANCH_NAME == 'master' ? ciMasterVersionRead() : escapedBranch
    return tag
}