def call(project, chartVersion, museumAddr) {
    sh "helm package helm/${project}"
    package = "${project}-${chartVersion}.tgz"
    if (chartVersion == "") {
        package = sh returnStdout: true, script: "ls project*"
    }
    withCredentials([usernamePassword(
        credentialsId: "chartmuseum",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """curl -u $USER:$PASS \
            --data-binary "@${package}" \
        http://${museumAddr}/api/charts"""
    }
}