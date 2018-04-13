def call(String project) {
    /***************************************
     * Build images
     **************************************/
    // Build image for linux-amd64
    sh "docker image build --build-arg PLATFORM=linux-amd64 -t dockerflow/${project}:linux-amd64 ."
    
    // Build image for test 
    sh "docker image build -t dockerflow/${project}-test -f Dockerfile.test ."
    
    // Build image for docs
    sh "docker image build -t dockerflow/${project}-docs -f Dockerfile.docs ."
    
    // Download and extract qemu-arm-static 
    sh "docker run --rm --privileged multiarch/qemu-user-static:register --reset"
    sh "mkdir tmp"
    sh """bash -c "pushd tmp
        curl -L -o qemu-arm-static.tar.gz https://github.com/multiarch/qemu-user-static/releases/download/v2.11.0/qemu-arm-static.tar.gz
        tar xzf qemu-arm-static.tar.gz
        popd"
    """
    
    // Build docker image for linux-arm
    sh "docker image build --build-arg PLATFORM=linux-arm -t dockerflow/${project}:linux-arm -f Dockerfile.linux-arm ."
    
    /***************************************
     * Tag images
     **************************************/
    // Tag image for linux-amd64
    sh "docker tag dockerflow/${project}:linux-amd64 dockerflow/${project}:${currentBuild.displayName}-linux-amd64"
    
    // Tag image for linux-arm
    sh "docker tag dockerflow/${project}:linux-arm dockerflow/${project}:${currentBuild.displayName}-linux-arm"
    
    // Tag image for docs 
    sh "docker tag dockerflow/${project}-docs dockerflow/${project}-docs:${currentBuild.displayName}"
    
    // Tag image for beta 
    sh "docker tag dockerflow/${project} dockerflow/${project}:beta"
    
    /***************************************
     * Push images
     **************************************/
    dfLogin()
    
    // Push image for beta 
    sh "docker image push dockerflow/${project}:beta"
    
    // Push image for test 
    sh "docker image push dockerflow/${project}-test"
    
    dockerLogout()
}
