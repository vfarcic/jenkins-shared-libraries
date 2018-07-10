def call(project) {
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME}"
    sh """helm delete ${chartName} \
        --tiller-namespace ${project}-build \
        --purge"""
}