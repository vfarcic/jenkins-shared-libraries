 def call(image) {
    tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}"
    sh """sudo docker pull \
        ${image}:${tagBeta}"""
    sh """sudo docker image tag \
        ${image}:${tagBeta} \
        ${image}:${currentBuild.displayName}"""
    sh """sudo docker image tag \
        ${image}:${tagBeta} \
        ${image}:latest"""
    withCredentials([usernamePassword(
        credentialsId: "docker",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """sudo docker login \
        -u $USER -p $PASS"""
    }
    sh """sudo docker image push \
        ${image}:${currentBuild.displayName}"""
    sh """sudo docker image push \
        ${image}:latest"""
 }