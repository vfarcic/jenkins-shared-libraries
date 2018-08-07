def call() {
    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")
    tagBeta = env.BRANCH_NAME == 'master' ? ciMasterVersionRead() : escapedBranch
}