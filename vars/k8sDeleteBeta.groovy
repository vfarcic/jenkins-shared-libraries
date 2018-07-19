def call(project) {
    chartName = "${project}-${env.BUILD_NUMBER}-${env.BRANCH_NAME.toLowerCase()}"
    sh """helm delete ${chartName} \
        --tiller-namespace ${project}-build \
        --purge"""
}