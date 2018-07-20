def call(project, address) {
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    sh """oc -n go-demo-3-build \
        create route edge \
        --service ${chartName.toLowerCase()} \
        --insecure-policy Allow \
        --hostname ${address}"""
}