def call(project) {
    chartName = ciChartNameRead(project)
    echo " waiting for deployment rollout:  $chartName in namespace: ${project}-build"

    sh """kubectl -n ${project}-build \
        rollout status deployment \
        ${chartName.toLowerCase()}"""
}