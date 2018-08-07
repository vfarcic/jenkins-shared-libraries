def call(project, domain) {
    addr = ciAddressRead(project, domain)
    sh "go get -d -v -t"
    sh """ADDRESS=${addr} \
        go test ./... -v \
        --run FunctionalTest"""
}
