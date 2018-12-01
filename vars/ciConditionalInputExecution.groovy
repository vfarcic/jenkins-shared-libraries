def call(Map config, Closure thunk) {
    def userInput = input(
        id: config.id,
        message: config.message,
        ok: config.ok,
        parameters: [[$class: 'BooleanParameterDefinition', description: '', name: config.name]])
    if(userInput == true) {
        thunk()
    } else {
        echo "noop"
    }

}