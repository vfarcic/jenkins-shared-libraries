def call(project, addr) {
    sh """helm upgrade \
        ${project} \
        helm/${project} -i \
        --tiller-namespace ${project}-build \
        --namespace ${project} \
        --set image.tag=${currentBuild.displayName} \
        --set ingress.host=${addr} \
        --reuse-values"""
}