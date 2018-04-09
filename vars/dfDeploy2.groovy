def call(String project, String serviceName, String docsServiceName) {
    sh "docker service update --image dockerflow/${project}:${currentBuild.displayName} ${serviceName}"
    script {
        if (docsServiceName != "") {
            sh "docker service update --image dockerflow/${project}-docs:${currentBuild.displayName} ${docsServiceName}"
        }
    }
}