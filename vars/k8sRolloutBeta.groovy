def call(project) {
    chartName = ciChartNameRead(project)
    echo " waiting for deployment rollout:  $chartName in namespace: jenkins"

    sh """kubectl -n jenkins \
        rollout status deployment \
        ${chartName.toLowerCase()}"""
}