def call(String project) {
    sh "docker image build -t dockerflow/${project} ."
    sh "docker image build -t dockerflow/${project}-test -f Dockerfile.test ."
    sh "docker image build -t dockerflow/${project}-docs -f Dockerfile.docs ."
    sh "docker tag dockerflow/${project} dockerflow/${project}:${currentBuild.displayName}"
    sh "docker tag dockerflow/${project}-docs dockerflow/${project}-docs:${currentBuild.displayName}"
    sh "docker tag dockerflow/${project} dockerflow/${project}:beta"
    dfLogin()
    sh "docker image push dockerflow/${project}:beta"
    sh "docker image push dockerflow/${project}-test"
    dockerLogout()
}