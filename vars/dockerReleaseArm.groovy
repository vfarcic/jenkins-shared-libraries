def call(String project, String hubUser) {
    sh "docker run --rm --privileged multiarch/qemu-user-static:register --reset"
    sh "mkdir tmp"
    sh "pushd tmp"
    sh "curl -L -o qemu-arm-static.tar.gz https://github.com/multiarch/qemu-user-static/releases/download/v2.11.0/qemu-arm-static.tar.gz"
    sh "tar xzf qemu-arm-static.tar.gz"
    sh "popd"
    sh "docker image build --build-arg PLATFORM=linux-arm -f Dockerfile.linux-arm -t ${hubUser}/${project}:${env.BUILD_NUMBER}-arm ."
    sh "docker image push ${hubUser}/${project}:${env.BUILD_NUMBER}-arm"
    sh "docker image tag ${hubUser}/${project}:${env.BUILD_NUMBER}-arm ${hubUser}/${project}:latest-arm"
    sh "docker image push ${hubUser}/${project}:latest-arm"
}