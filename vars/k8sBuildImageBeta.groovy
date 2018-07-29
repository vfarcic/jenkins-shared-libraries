def call(image, sudo = true) {
    tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}"
    prefix = ""
    if (sudo) {
        prefix = "sudo "
    }
    sh """${prefix}docker image build \
        -t ${image}:${tagBeta} ."""
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