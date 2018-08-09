def call() {
    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")

    tag = ""
    if(env.BRANCH_NAME == 'master') {
        echo "##### master branch detected"
        tag = ciBumpUpVersion(ciMasterVersionRead(), "minor")
    } else if (env.BRANCH_NAME.toString().startsWith("hotfix")) {
        echo "##### hotfix branch detected"
        tag = ciBumpUpVersion(ciMasterVersionRead(), "revision")
    } else  {
        echo "##### feature branch detected"
        tag = escapedBranch
    }

    echo "version set to $tag"
    return tag
}