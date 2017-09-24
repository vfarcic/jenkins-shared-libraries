def call(String project) {
    sh "docker image build -t vfarcic/${project} ."
    sh "docker image build -t vfarcic/${project}-test -f Dockerfile.test ."
    sh "docker image build -t vfarcic/${project}-docs -f Dockerfile.docs ."
    sh "docker tag vfarcic/${project} vfarcic/${project}:${currentBuild.displayName}"
    sh "docker tag vfarcic/${project}-docs vfarcic/${project}-docs:${currentBuild.displayName}"
    sh "docker tag vfarcic/${project} vfarcic/${project}:beta"
    dockerLogin()
    sh "docker image push vfarcic/${project}:beta"
    sh "docker image push vfarcic/${project}-test"
    dockerLogout()
}