def call() {
    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")

    tag = ""
    if(env.BRANCH_NAME == 'master') {
        tag = bumpUpVersion(ciMasterVersionRead(), "minor")
    } else if (env.BRANCH_NAME.toString().startsWith("hotfix")) {
        tag = bumpUpVersion(ciMasterVersionRead(), "revision")
    } else  {
        tag = escapedBranch
    }

    return tag
}