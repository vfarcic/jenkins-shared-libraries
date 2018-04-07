def call() {
    withCredentials([usernamePassword(
            credentialsId: "dh-docker-flow",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh "docker login -u $USER -p $PASS"
    }
}