def call(project) {
    chartName = ciChartNameRead(project)
    sh """kubectl -n ${project}-build \
        rollout status deployment \
        ${chartName.toLowerCase()}"""
}