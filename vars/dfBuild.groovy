def call(String project) {
    sh "docker image build -t vfarcic/${project} ."
    sh "docker image build -t vfarcic/${project}-docs -f Dockerfile.docs ."
    sh "docker tag vfarcic/docker-flow-proxy vfarcic/${project}:${currentBuild.displayName}"
    sh "docker tag vfarcic/docker-flow-proxy-docs vfarcic/${project}-docs:${currentBuild.displayName}"
}