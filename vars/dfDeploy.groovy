def call(String serviceName, String docsServiceName) {
    sh "docker service update --image vfarcic/docker-flow-proxy:${currentBuild.displayName} ${serviceName}"
    sh "docker service update --image vfarcic/docker-flow-proxy-docs:${currentBuild.displayName} ${docsServiceName}"
}