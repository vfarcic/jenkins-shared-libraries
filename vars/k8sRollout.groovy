def call(project) {
    sh """kubectl -n ${project} \
        rollout status deployment \
        ${project}"""
}