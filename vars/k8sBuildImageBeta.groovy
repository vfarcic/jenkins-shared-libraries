def call(image) {
    tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}"
    sh """sudo docker image build \
        -t ${image}:${tagBeta} ."""
    withCredentials([usernamePassword(
        credentialsId: "docker",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """sudo docker login \
            -u $USER -p $PASS"""
    }
    sh """sudo docker image push \
        ${image}:${tagBeta}"""
}