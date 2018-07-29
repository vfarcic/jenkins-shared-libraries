def call(project, chartVersion, museumAddr, replaceTag = false) {
    sh "helm package helm/${project}"
    packageName = "${project}-${chartVersion}.tgz"
    if (chartVersion == "") {
        packageName = sh(returnStdout: true, script: "ls ${project}*").trim()
    }
    if (replaceTag) {
        yaml = readYaml file: "helm/${project}/values.yaml"
        echo "${yaml.image.tag}"
    }
    withCredentials([usernamePassword(
        credentialsId: "chartmuseum",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {
        sh """curl -u $USER:$PASS \
            --data-binary "@${packageName}" \
        http://${museumAddr}/api/charts"""
    }
}