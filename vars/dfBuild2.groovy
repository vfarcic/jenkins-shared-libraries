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

    // Build image for linux-amd64
    // sh "docker image build --build-arg PLATFORM=linux-amd64 -t dockerflow/${project}:linux-amd64 ."
    
    // sh "docker image build -t dockerflow/${project}-test -f Dockerfile.test ."
    // sh "docker image build -t dockerflow/${project}-docs -f Dockerfile.docs ."
    
    // // Tag image for linux-amd64
    // sh "docker tag dockerflow/${project}:linux-amd64 dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
    
    // sh "docker tag dockerflow/${project}-docs dockerflow/${project}-docs:${currentBuild.displayName}"
    // sh "docker tag dockerflow/${project}:linux-amd64 dockerflow/${project}:beta"
    
    // dfLogin()
    // sh "docker image push dockerflow/${project}:beta"
    // sh "docker image push dockerflow/${project}-test"
    dockerLogout()
}
