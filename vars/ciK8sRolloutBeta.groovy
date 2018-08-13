def call(project) {
    chartName = ciChartNameRead(project)
    echo " waiting for deployment rollout:  $chartName in namespace: jenkins"

    //todo replace jenkins here, its just my env is weird
    sh """kubectl -n jenkins \
        rollout status deployment \
        ${chartName.toLowerCase()}"""
}