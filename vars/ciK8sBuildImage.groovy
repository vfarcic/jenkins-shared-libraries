def call(image, sudo = true, tags = []) {

    escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")
    tagBeta = env.BRANCH_NAME == 'master' ? ciVersionRead() : escapedBranch


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

    tags.each { tag ->
        sh """${pefix} docker tag ${image}:${tagBeta} ${image}:${tag}"""
        sh """${pefix} docker push ${image}:${tag}"""
    }

}