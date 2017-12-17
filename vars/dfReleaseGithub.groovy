def call(String project) {
    sh "docker container run --rm -v \${PWD}:/src vfarcic/gox docker-flow-proxy"
    withCredentials([usernamePassword(credentialsId: "github-token-2", usernameVariable: "USER", passwordVariable: "GITHUB_TOKEN")]) {
        script {
            def msg = sh(returnStdout: true, script: "git log --format=%B -1").trim()
            msg = """release title
release msg [release]"""
            if (msg.contains("[release]")) {
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
                def cmd = "docker container run --rm -e GITHUB_TOKEN=${GITHUB_TOKEN} -v \${PWD}:/src -w /src vfarcic/github-release"
                sh "${cmd} git tag -a ${currentBuild.displayName} -m '${releaseMsg}'"
                sh "${cmd} git push --tags"
                sh "${cmd} github-release release --user vfarcic --repo ${project} --tag ${currentBuild.displayName} --name '${releaseTitle}' --description '${releaseMsg}'"
                files = findFiles(glob: "${project}_*")
                for (def file : files) {
                    sh "${cmd} github-release upload --user vfarcic --repo ${project} --tag ${currentBuild.displayName} --name '${file.name}' --file ${file.name}"
                }
            }
        }
    }
}
