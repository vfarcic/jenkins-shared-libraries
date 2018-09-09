def call(project, chartVersion, museumAddr, replaceTag = false, failIfExists = false) {
    withCredentials([usernamePassword(credentialsId: "chartmuseum", usernameVariable: "USER", passwordVariable: "PASS")]) {
        if (failIfExists) {
            yaml = readYaml file: "helm/${project}/Chart.yaml"
            out = sh returnStdout: true, script: "curl -u $USER:$PASS http://${museumAddr}/api/charts/${project}/${yaml.version}"
            if (!out.contains("error")) {
                error "Did you forget to increment the Chart version?"
            }
        }
        if (replaceTag) {
            yaml = readYaml file: "helm/${project}/values.yaml"
            yaml.image.tag = currentBuild.displayName
            sh "rm -f helm/${project}/values.yaml"
            writeYaml file: "helm/${project}/values.yaml", data: yaml
        }

//        sh "helm repo add chartmuseum http://${museumAddr} --username admin --password admin"
        sh "helm repo add chartmuseum http://${museumAddr} "
        sh "helm push helm/go-demo-3/ --version=${chartVersion.replace("v","")} chartmuseum --username admin --password admin"
    }
}
