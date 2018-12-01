def call(version) {
    //Give a chance for a custom version
    def newVersion = version
    try {
        // change to a convenient timeout for you
        timeout(time: 15, unit: 'SECONDS') {
            newVersion = input(
                    id: 'SuggestVersion', message: 'Build auto version ?', parameters: [
                    [$class: 'StringParameterDefinition',
                     defaultValue: version,
                     description: '',
                     name: 'Confirm release version ']
            ])
        }
    } catch(err) { }

    echo "user confirmed build version: $newVersion"
    return newVersion
}