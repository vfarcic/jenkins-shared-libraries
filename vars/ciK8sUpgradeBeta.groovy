def call(project, domain, extraValues = "") {
    tag = ciVersionRead()
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    addr = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    sh """helm upgrade \
        ${chartName.toLowerCase()} \
        helm/${project} -i \
        --tiller-namespace ${project}-build \
        --set image.tag=${tag} \
        --set ingress.host=${addr.toLowerCase()} \
        ${extraValues}"""
}