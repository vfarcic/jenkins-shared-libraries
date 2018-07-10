def call(address) {
    sh "go get -d -v -t"
    sh """DURATION=1 ADDRESS=${address} \
        go test ./... -v \
        --run ProductionTest"""
}
