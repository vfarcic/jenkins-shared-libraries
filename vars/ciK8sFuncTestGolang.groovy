def call(project, domain) {
    addr = ciAddressRead(project, domain)
    echo "running func test on: $addr"

    sh "go get -d -v -t"
    sh """ADDRESS=${addr} \
        go test ./... -v \
        --run FunctionalTest"""
}
