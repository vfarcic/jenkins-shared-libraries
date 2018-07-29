def call(project, chartVersion, museumAddr) {
    sh "helm package helm/${project}"
    packageName = "${project}-${chartVersion}.tgz"
    if (chartVersion == "") {
        packageName = sh returnStdout: true, script: "ls ${project}\*"
    }
    withCredentials([usernamePassword(
        credentialsId: "chartmuseum",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """curl -u $USER:$PASS \
            --data-binary "@helm/${packageName}" \
        http://${museumAddr}/api/charts"""
    }
}