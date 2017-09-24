def call(String project, String serviceName, String docsServiceName) {
    sh "docker service update --image vfarcic/${project}:${currentBuild.displayName} ${serviceName}"
    script {
        if (docsServiceName != "") {
            sh "docker service update --image vfarcic/${project}-docs:${currentBuild.displayName} ${docsServiceName}"
        }
    }
}