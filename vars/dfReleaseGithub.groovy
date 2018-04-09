def call(String project) {
    withCredentials([usernamePassword(
        credentialsId: "github-token-2",
        usernameVariable: "GITHUB_USER",
        passwordVariable: "GITHUB_TOKEN"
    )]) {
        script {
            def msg = sh(returnStdout: true, script: "git log --format=%B -1").trim()
            if (msg.contains("[release]")) {
                echo "Creating a release!"
                msg = msg.replace("[release]", "")
                def lines = msg.split("\n")
                def releaseTitle = ""
                def releaseMsg = ""
                for (i = 0; i <lines.length; i++) {
                    if (i == 0) {
                        releaseTitle = lines[i]
                    } else {
                        releaseMsg = lines[i] + "\n"
                    }
                }
            } else {
                echo "Not creating a release!"
            }
            echo "000"
            sh "docker container run --rm -v \${PWD}:/src vfarcic/gox ${project}"
            echo "111"
            if (msg.contains("[release]")) {
                sh "git tag -a ${currentBuild.displayName} -m '${releaseMsg}'"
                sh "git push https://${GITHUB_USER}:${GITHUB_TOKEN}@github.com/${GITHUB_USER}/${project}.git --tags"
                def cmd = """docker container run --rm \
                    -e GITHUB_TOKEN=${GITHUB_TOKEN} \
                    -v \${PWD}:/src -w /src \
                    vfarcic/github-release"""
                sh """${cmd} github-release release --user vfarcic \
                    --repo ${project} --tag ${currentBuild.displayName} \
                    --name '${releaseTitle}' --description '${releaseMsg}'"""
                files = findFiles(glob: "${project}_*")
                for (def file : files) {
                    sh """${cmd} github-release upload \
                        --user vfarcic --repo ${project} \
                        --tag ${currentBuild.displayName} \
                        --name '${file.name}' \
                        --file ${file.name}"""
                }
            }
            echo "222"
        }
    }
}
