def call(String image, String tag) {
    sh "sudo docker image build -t ${image}:${tag} ."
    withCredentials([usernamePassword(
        credentialsId: "docker",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh "sudo docker login -u $USER -p $PASS"
    }
    sh "sudo docker image push ${image}:${tag}"
}