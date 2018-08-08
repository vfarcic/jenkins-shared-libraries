def call(tag, sudo = true, extratags = []) {

    prefix = ""
    if (sudo) {
        prefix = "sudo "
    }

    withCredentials([usernamePassword(
            credentialsId: "docker",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh """${prefix}docker login \
            -u $USER -p $PASS"""
    }

    extraTags.each { t ->
        sh """${prefix} docker tag ${image}:${tag} ${image}:${t}"""
        sh """${prefix} docker push ${image}:${t}"""
    }

}