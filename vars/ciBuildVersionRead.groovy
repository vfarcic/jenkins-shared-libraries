def call() {

    def version = ciVersionRead()

    if(env.BRANCH_NAME == 'master' || env.BRANCH_NAME.startsWith("hotfix")) {
        version = "RC-" + version + "-b${env.BUILD_ID}"
    }

    echo "build version set to $version"
    return version
}