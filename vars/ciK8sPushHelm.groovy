def call(project, chartVersion, museumAddr, replaceTag = false, failIfExists = false) {
    withCredentials([usernamePassword(credentialsId: "chartmuseum", usernameVariable: "USER", passwordVariable: "PASS")]) {


        ciUpdateYamlFile(
                yamlFile: "helm/${project}/Chart.yaml",
                params: ["version": chartVersion]
        )

        if (failIfExists) {
            yaml = readYaml file: "helm/${project}/Chart.yaml"
            out = sh returnStdout: true, script: "curl -u $USER:$PASS http://${museumAddr}/api/charts/${project}/${yaml.version}"
            if (!out.contains("error")) {
                error "Did you forget to increment the Chart version?"
            }
        }

        if (replaceTag) {
            ciUpdateYamlFile(
                    yamlFile: "helm/${project}/values.yaml",
                    params: ["image.tag": chartVersion]
            )
        }
        
        sh "helm package helm/${project}"
        packageName = "${project}-${chartVersion}.tgz"

        sh """curl -u $USER:$PASS --data-binary "@${packageName}" http://${museumAddr}/api/charts"""
    }
}
