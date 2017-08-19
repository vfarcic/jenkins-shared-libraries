def call(String project, String hubUser, String hostIp, String basePath) {
    sh "TAG=beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER} SERVICE_PATH=${basePath}-${env.BRANCH_NAME}-${env.BUILD_NUMBER} docker stack deploy -c stack-test.yml ${project}-beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    sh "docker image build -f Dockerfile.test -t ${hubUser}/${project}-test:${env.BRANCH_NAME}-${env.BUILD_NUMBER} ."
    sh "docker image push ${hubUser}/${project}-test:${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    sleep 10
    sh "TAG=${env.BRANCH_NAME}-${env.BUILD_NUMBER} HOST_IP=${hostIp} SERVICE_PATH=${basePath}-${env.BRANCH_NAME}-${env.BUILD_NUMBER} docker-compose -p ${project}-${env.BRANCH_NAME}-${env.BUILD_NUMBER} run --rm functional"
}