def call(binary) {
    sh "go get -d -v -t"
    sh "go test --cover -v ./... --run UnitTest"
    sh "go build -v -o ${binary}"
}
