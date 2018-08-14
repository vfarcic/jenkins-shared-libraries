def call(id, message, ok, name, Closure tunk) {
    def userInput = input(id: id,
            message: message,
            ok: ok,
            parameters: [[$class: 'BooleanParameterDefinition',  description: '', name: name]])

    if(userInput == true) {
        tunk()
    } else {
        echo "noop"
    }
}