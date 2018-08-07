def call(image, sudo = true, tags = []) {

    tag = ciVersionRead()

    prefix = ""
    if (sudo) {
        prefix = "sudo "
    }
    sh """${prefix}docker image build \
        -t ${image}:${tag} ."""

    withCredentials([usernamePassword(
            credentialsId: "docker",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh """${prefix}docker login \
            -u $USER -p $PASS"""
    }
    sh """${prefix}docker image push \
        ${image}:${tag}"""

    tags.each { t ->
        sh """${prefix} docker tag ${image}:${tag} ${image}:${t}"""
        sh """${prefix} docker push ${image}:${t}"""
    }

}