def call(Closure thunk) {
    if(ciCheckReleaseBranches()) {
        thunk()
    }
}