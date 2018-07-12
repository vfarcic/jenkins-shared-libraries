def call(project, domain) {
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    tagBeta = "${currentBuild.displayName}-${env.BRANCH_NAME}"
    addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    sh """helm upgrade \
        ${chartName} \
        helm/${project} -i \
        --tiller-namespace ${project}-build \
        --set image.tag=${tagBeta} \
        --set ingress.host=${addr.toLowerCase()}"""
}