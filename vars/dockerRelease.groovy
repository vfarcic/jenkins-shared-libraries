def call(String project, String hubUser) {
    sh "docker image tag ${hubUser}/${project}:beta-${env.BUILD_NUMBER} ${hubUser}/${project}:${env.BUILD_NUMBER}"
    sh "docker image push ${hubUser}/${project}:${env.BUILD_NUMBER}"
    sh "docker image tag ${hubUser}/${project}:beta-${env.BUILD_NUMBER} ${hubUser}/${project}:latest"
    sh "docker image push ${hubUser}/${project}:latest"
}