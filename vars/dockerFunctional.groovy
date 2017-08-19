def call(String project, String hubUser, String hostIp, String basePath) {
    sh "TAG=beta-${env.BUILD_NUMBER} SERVICE_PATH=${basePath}-${env.BUILD_NUMBER} docker stack deploy -c stack-test.yml ${project}-beta-${env.BUILD_NUMBER}"
    sleep 10
    sh "docker image build -f Dockerfile.test -t ${hubUser}/${project}-test:${env.BUILD_NUMBER} ."
    sh "docker image push ${hubUser}/${project}-test:${env.BUILD_NUMBER}"
    sh "TAG=${env.BUILD_NUMBER} HOST_IP=${hostIp} SERVICE_PATH=${basePath}-${env.BUILD_NUMBER} docker-compose -p ${project}-${env.BUILD_NUMBER} run --rm functional"
}