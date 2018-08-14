def call(project, namespace) {
    sh """kubectl -n ${namespace} \
        rollout status deployment \
        ${project}"""
}