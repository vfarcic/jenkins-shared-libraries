def call(project, chartVersion, museumAddr, replaceTag = false) {
    if (replaceTag) {
        yaml = readYaml file: "helm/${project}/values.yaml"
        yaml.image.tag = currentBuild.displayName
        sh "rm -f helm/${project}/values.yaml"
        writeYaml file: "helm/${project}/values.yaml", data: yaml
    }
    sh "helm package helm/${project}"
    packageName = "${project}-${chartVersion}.tgz"
    if (chartVersion == "") {
        packageName = sh(returnStdout: true, script: "ls ${project}*").trim()
    }
    withCredentials([usernamePassword(credentialsId: "chartmuseum", usernameVariable: "USER", passwordVariable: "PASS")]) {
        sh """curl -u $USER:$PASS --data-binary "@${packageName}" http://${museumAddr}/api/charts"""
    }
}