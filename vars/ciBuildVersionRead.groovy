def call() {

    def version = ciVersionRead()

    if(ciCheckReleaseBranches()) {
        version = "RC-" + version + "-b${env.BUILD_ID}"
    }

    echo "build version set to $version"
    return version
}