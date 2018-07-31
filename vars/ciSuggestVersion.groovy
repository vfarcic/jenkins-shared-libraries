def call(version) {
    //Give a chance for a custom version
    try {
        timeout(time: 15, unit: 'SECONDS') { // change to a convenient timeout for you
            env.newVersion = input(
                    id: 'SuggestVersion', message: 'Build auto version ?', parameters: [
                    [$class: 'StringParameterDefinition',
                     defaultValue: version,
                     description: '',
                     name: 'Confirm release version ']
            ])
        }
    } catch(err) { }

    return env.newVersion
}