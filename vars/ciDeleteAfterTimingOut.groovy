def call(time, unit, Closure tunk) {
    try {
        timeout(time: time, unit: unit) {
            tunk()
        }
    }
    catch (err) {
        if ('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            container('helm') {
                k8sDeleteBeta(params.project)
            }
        } else {
            currentBuild.result = 'FAILURE'
        }

    }
}