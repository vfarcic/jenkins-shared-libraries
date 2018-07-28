def call(project, chartVersion, museumAddr) {
    sh "helm package helm/${project}"
    withCredentials([usernamePassword(
        credentialsId: "chartmuseum",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        def package
        if (chartVersion == "") {
            package = sh returnStdout: true, script: "ls project*"
        } else {
            package = "${project}-${chartVersion}.tgz"
        }
        sh """curl -u $USER:$PASS \
            --data-binary "@${package}" \
        http://${museumAddr}/api/charts"""
    }
}