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
    
    // Tag image for linux-arm
    sh "docker tag dockerflow/${project}:linux-arm dockerflow/${project}:${currentBuild.displayName}-linux-arm"
}
