def call(project) {
    chartName = ciChartNameRead(project)
    sh """helm delete ${chartName.toLowerCase()} \
        --tiller-namespace ${project}-build \
        --purge"""
}