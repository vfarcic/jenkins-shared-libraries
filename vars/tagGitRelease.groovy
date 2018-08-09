def call(tag) {
    withCredentials([sshUserPrivateKey(credentialsId: 'TM_RSA', keyFileVariable: 'SSH_KEY')]) {
        sh 'echo ssh -i $SSH_KEY -l git -o StrictHostKeyChecking=no \\"\\$@\\" > local_ssh.sh'
        sh 'chmod +x local_ssh.sh'
        withEnv(['GIT_SSH=local_ssh.sh']) {

            //ugly, but could not make it work globally... jenkins sometimes makes me hate my life
            sh """ git config --global user.email "tigran@mna.com" """
            sh """ git config --global user.name "Tigran Mna" """

            sh "git tag -a $tag ${env.GIT_COMMIT}"
            sh "git push origin $tag "
        }
    }
}