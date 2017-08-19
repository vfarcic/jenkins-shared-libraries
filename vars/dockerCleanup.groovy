def call(String project) {
    sh "docker stack rm ${project}-beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    sh "docker system prune -f"
}