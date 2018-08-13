def call(time, unit, Closure tunk) {
    try {
        timeout(time: time, unit: unit) {
            tunk()
        }
    }
    catch (err) {
        def user = err.getCauses()[0].getUser()
        if ('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            echo "no input was received before timeout"
            container('helm') {
                ciK8sDeleteBeta(er.project)
            }
            currentBuild.result = 'SUCCESS'
        } else if (env.userInput != null) {
            echo "input was received from user"
            currentBuild.result = 'SUCCESS'
        } else {
            echo "this was not successful"
            currentBuild.result = 'FAILURE'
        }
    }
}