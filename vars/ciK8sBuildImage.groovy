def call() {
    sh "git fetch origin 'refs/tags/*:refs/tags/*'"
    def version = sh(script: 'git tag -l | tail -n1', returnStdout: true).trim() ?: 'v1.0.0'
    def parser = /(?<major>v\d+).(?<minor>\d+).(?<revision>\d+)/
    def match = version =~ parser
    match.matches()
    def (major, minor, revision) = ['major', 'minor', 'revision'].collect { match.group(it) }
    def nextVersion = "${major + "." + (minor.toInteger() + 1)}" + "." + revision

    return nextVersion
}