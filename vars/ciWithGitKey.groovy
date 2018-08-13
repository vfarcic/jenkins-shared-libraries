def call(credentialId, Closure tunk) {
    withCredentials([sshUserPrivateKey(credentialsId: credentialId, keyFileVariable: 'SSH_KEY')]) {
        sh 'echo ssh -i $SSH_KEY -l git -o StrictHostKeyChecking=no \\"\\$@\\" > local_ssh.sh'
        sh 'chmod +x local_ssh.sh'

        withEnv(['GIT_SSH=local_ssh.sh']) {
            tunk()
        }
    }
}