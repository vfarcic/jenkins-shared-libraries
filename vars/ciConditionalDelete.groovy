def call() {
    def userInput = input(id: "delete deployment",
            message: "Delete ${env.BRANCH_NAME} deployment?",
            ok: 'Delete',
            parameters: [[$class: 'BooleanParameterDefinition',  description: '', name: 'Delete']])

    if(userInput == true) {
        ciK8sDeleteBeta(params.project)
    } else {
        echo "[${user}] kept the deployment alive "
    }
}