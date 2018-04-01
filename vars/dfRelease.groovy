def call(String project, gox = false) {
    dockerLogin()
    sh "docker image push vfarcic/${project}:latest"
    sh "docker image push vfarcic/${project}:${currentBuild.displayName}"
    sh "docker image push vfarcic/${project}-docs:latest"
    sh "docker image push vfarcic/${project}-docs:${currentBuild.displayName}"
    
    sh """bash -c "
        curl -L -o manifest-tool https://github.com/estesp/manifest-tool/releases/download/v0.7.0/manifest-tool-linux-amd64
        chmod +x manifest-tool"
    """
    
    sh """./manifest-tool push from-args --platforms linux/arm --template "vfarcic/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}:${currentBuild.displayName}""""
    sh """./manifest-tool push from-args --platforms linux/arm --template "vfarcic/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}:latest""""
    sh """./manifest-tool push from-args --platforms linux/arm --template "vfarcic/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}-docs:${currentBuild.displayName}""""
    sh """./manifest-tool push from-args --platforms linux/arm --template "vfarcic/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}-docs:latest""""
    dockerLogout()
}
