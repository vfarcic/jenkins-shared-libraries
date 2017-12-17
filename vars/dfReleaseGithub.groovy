def call(String project) {
    sh "docker container run --rm -v ${pwd}:/src vfarcic/gox docker-flow-proxy"
    withCredentials([usernamePassword(credentialsId: "github-token-2", usernameVariable: "USER", passwordVariable: "GITHUB_TOKEN")]) {
        script {
            def msg = sh(returnStdout: true, script: "git log --format=%B -1").trim()
            if (msg.contains("[release]")) {
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
                sh "docker container run --rm -e GITHUB_TOKEN=${GITHUB_TOKEN} -v ${pwd}:/src -w /src vfarcic/github-release git tag -a ${currentBuild.displayName} -m '${releaseMsg}'"
                sh "docker container run --rm -e GITHUB_TOKEN=${GITHUB_TOKEN} -v ${pwd}:/src -w /src vfarcic/github-release git push --tags"
                sh "docker container run --rm -e GITHUB_TOKEN=${GITHUB_TOKEN} -v ${pwd}:/src -w /src vfarcic/github-release github-release release --user vfarcic --repo ${project} --tag ${currentBuild.displayName} --name '${releaseTitle}' --description '${releaseMsg}'"
                files = findFiles(glob: "${project}_*")
                println files
                println "---"
                for (def file : files) {
                    println file
                    sh "docker container run --rm -e GITHUB_TOKEN=${GITHUB_TOKEN} -v ${pwd}:/src -w /src vfarcic/github-release github-release upload --user vfarcic --repo ${project} --tag ${currentBuild.displayName} --name '${file.name}' --file ${file.name}"
                }
            }
        }
    }
}
