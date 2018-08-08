def call(version, semantic) {

    echo "bumping up version: $version"

    def parser = /(?<major>v\d+).(?<minor>\d+).(?<revision>\d+)/
    def match = version =~ parser
    match.matches()
    def (major, minor, revision) = ['major', 'minor', 'revision'].collect { match.group(it) }

    nextVersion = version
    if(semantic == "major") {
        nextVersion = "${(major.toInteger() + 1)}" + "." + minor + "." + revision
    } else if(semantic == "minor") {
        nextVersion = "${major}" + "." + "${(minor.toInteger() + 1)}" + "." + revision
    } else {
        nextVersion = "${major}" + "." + minor + "." + ${revision.toInteger() + 1}
    }

    userNextVersion = ciSuggestVersion(nextVersion)
    return userNextVersion
}