def call(project, domain) {
    echo "${project}"
    echo "${domain}"
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    echo "${chartName}"
    addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    sh """oc -n ${project}-build \
        create route edge \
        --service ${chartName.toLowerCase()} \
        --insecure-policy Allow \
        --hostname ${addr}"""
}