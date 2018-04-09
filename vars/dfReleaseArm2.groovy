def call(String project) {
    sh "docker run --rm --privileged multiarch/qemu-user-static:register --reset"
    sh "mkdir tmp"
    sh """bash -c "pushd tmp
        curl -L -o qemu-arm-static.tar.gz https://github.com/multiarch/qemu-user-static/releases/download/v2.11.0/qemu-arm-static.tar.gz
        tar xzf qemu-arm-static.tar.gz
        popd"
    """
    
    // Build docker image for linux-arm
    sh "docker image build --build-arg PLATFORM=linux-arm -t dockerflow/${project}:linux-arm -f Dockerfile.linux-arm ."
    
    // login docker
    dockerLogin()
    
    // Tag docker image
    sh "docker image tag dockerflow/${project}:linux-arm dockerflow/${project}:${currentBuild.displayName}-linux-arm"
    
    // Push docker image
    sh "docker image push dockerflow/${project}:${currentBuild.displayName}-linux-arm"

    // Download manifest-tool
    sh """bash -c "
        curl -L -o manifest-tool https://github.com/estesp/manifest-tool/releases/download/v0.7.0/manifest-tool-linux-amd64
        chmod +x manifest-tool"
    """
    
    // Create and push manifest list
    sh '''./manifest-tool push from-args --platforms linux/arm --template "dockerflow/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}:${currentBuild.displayName}"'''    
    sh '''./manifest-tool push from-args --platforms linux/arm --template "dockerflow/${project}:${currentBuild.displayName}-OS-ARCH" --target "vfarcic/${project}:latest"'''

  dockerLogout()
}
