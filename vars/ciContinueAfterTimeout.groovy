def call(time, unit, Closure timeoutFunction = { println 'noop' } , Closure thunk) {
    
    try {
        timeout(time: time, unit: unit) {
            thunk()
        }
    }
    catch (err) {
        //https://stackoverflow.com/questions/50050076/jenkins-pipeline-java-io-notserializableexception-hudson-model-user-when-exec
        def user = err.getCauses()[0].getUser().toString()

        if ('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            timeoutFunction()
            currentBuild.result = 'SUCCESS'
        } else {
            echo "this was not successful"
            currentBuild.result = 'FAILURE'
        }
    }
}