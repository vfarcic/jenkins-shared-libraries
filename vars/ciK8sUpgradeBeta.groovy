def call(project, domain, tag, extraValues = "") {

    chartName = ciChartNameRead(project)
    addr = ciAddressRead(project, domain)

    sh """helm upgrade \
        ${chartName.toLowerCase()} \
        helm/${project} -i \
        --tiller-namespace ${project}-build \
        --namespace ${project}-build \
        --set image.tag=${tag} \
        --set ingress.host=${addr.toLowerCase()} \
        ${extraValues}"""
}