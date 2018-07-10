def call(project, chartVersion, museumAddr) {
    sh "helm package helm/${project}"
    withCredentials([usernamePassword(
        credentialsId: "chartmuseum",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """curl -u $USER:$PASS \
            --data-binary "@${project}-${chartVersion}.tgz" \
        http://${museumAddr}/api/charts"""
    }
}