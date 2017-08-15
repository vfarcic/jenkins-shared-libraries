def call(String project) {
    sh "docker stack rm ${project}-beta-${env.BUILD_NUMBER}"
    sh "docker system prune -f"
}