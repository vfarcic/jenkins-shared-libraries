def call() {

    def version = ciVersionRead()

    if(env.BRANCH_NAME == 'master' || env.BRANCH_NAME.startsWith("hotfix")) {
        version = version + "-${env.BUILD_ID}"
    }

    echo "build version set to $version"
    return version
}