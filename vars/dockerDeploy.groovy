def call(String project, String hubUser, String hostIp, String basePath) {
    script {
        try {
            sh "docker service update --image ${hubUser}/${project}:${env.BUILD_NUMBER} ${project}_main"
            sh "TAG=beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER} HOST_IP=${hostIp} SERVICE_PATH=${basePath}-${env.BRANCH_NAME}-${env.BUILD_NUMBER} docker-compose -p ${project}-${env.BRANCH_NAME}-${env.BUILD_NUMBER} run --rm production"
        } catch (e) {
            sh "docker service update --rollback ${project}_main"
            error "Failed to update the service"
        }
    }
}
