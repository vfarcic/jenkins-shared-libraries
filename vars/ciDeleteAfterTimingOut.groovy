def call(time, unit, Closure tunk) {
    try {
        timeout(time: time, unit: unit) {
            tunk()
        }
    }
    catch (err) {
        def user = err.getCauses()[0].getUser()
        if ('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            container('helm') {
                k8sDeleteBeta(params.project)
            }
            currentBuild.result = 'SUCCESS'
        } else {
            currentBuild.result = 'FAILURE'
        }

    }
}