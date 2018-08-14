def call() {
    def userInput = input(id: "delete deployment",
            message: "Delete ${env.BRANCH_NAME} deployment?",
            ok: 'proceed',
            parameters: [[$class: 'BooleanParameterDefinition',  description: '', name: 'Delete']])

    println (" ###############" + userInput )
    if(userInput == true) {
//        ciK8sDeleteBeta(params.project)
        echo "delete"
    } else {
        echo "[${user}] kept the deployment alive "
    }
}