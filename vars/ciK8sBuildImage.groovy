def call(image, sudo = true) {

    echo "test all this shit"
    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")
    tagBeta = env.BRANCH_NAME == 'master' ? ciVersionRead() : escapedBranch
    commitTag = env.shortGitCommit


    prefix = ""
    if (sudo) {
        prefix = "sudo "
    }
    sh """${prefix}docker image build \
        -t ${image}:${tagBeta} ."""

    sh """${prefix}docker tag \
        ${image}:${tagBeta} ${image}:${commitTag} """

    withCredentials([usernamePassword(
            credentialsId: "docker",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh """${prefix}docker login \
            -u $USER -p $PASS"""
    }
    sh """${prefix}docker image push \
        ${image}:${tagBeta}"""
}