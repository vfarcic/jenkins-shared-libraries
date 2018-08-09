def call(tunk) {
    withCredentials([sshUserPrivateKey(credentialsId: 'TM_RSA', keyFileVariable: 'SSH_KEY')]) {

        //for subsequent runs
        def exists = fileExists 'local_ssh.sh'

        echo "############### checking if file exist"
        if(!exists) {
            echo "############### file does not exist"
            sh 'echo ssh -i $SSH_KEY -l git -o StrictHostKeyChecking=no \\"\\$@\\" > local_ssh.sh'
            sh 'cat local_ssh.sh'
            sh 'chmod +x local_ssh.sh'
        }

        withEnv(['GIT_SSH_COMMAND=local_ssh.sh']) {
            echo "############### running tunk "
            tunk()
        }
    }
}