def call(tunk) {
    withCredentials([sshUserPrivateKey(credentialsId: 'TM_RSA', keyFileVariable: 'SSH_KEY')]) {

        // on the second run it dies with can't create local_ssh.sh: Permission denied
        sh 'rm -rf local_ssh.sh'

        sh 'echo ssh -i $SSH_KEY -l git -o StrictHostKeyChecking=no \\"\\$@\\" > local_ssh.sh'
        sh 'chmod +x local_ssh.sh'
        withEnv(['GIT_SSH=local_ssh.sh']) {
            tunk()
        }
    }
}