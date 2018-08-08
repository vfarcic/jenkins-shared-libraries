def call() {
    def version = sh(script: 'git tag -l | tail -n1', returnStdout: true).trim() ?: 'v1.0.0'
    echo "current release is: $version"

    return version
}