def call(domain) {
    addr = "go-demo-3-${env.BUILD_NUMBER}-${env.BRANCH_NAME}.${domain}"
    sh "go get -d -v -t"
    sh """ADDRESS=${addr} \
        go test ./... -v \
        --run FunctionalTest"""
}