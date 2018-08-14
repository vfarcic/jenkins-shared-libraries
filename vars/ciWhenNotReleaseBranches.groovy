def call(Closure tunk) {
    if(!ciCheckReleaseBranches()) {
        tunk()
    }
}