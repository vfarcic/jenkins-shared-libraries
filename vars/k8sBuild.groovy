def call() {
    sh "sudo docker image build -t ${env.IMAGE}:${env.TAG_BETA} ."
    withCredentials([usernamePassword(
        credentialsId: "docker",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh "sudo docker login -u $USER -p $PASS"
    }
    sh "sudo docker image push ${env.IMAGE}:${env.TAG_BETA}"
}