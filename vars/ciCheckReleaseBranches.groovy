def call() {
    return env.BRANCH_NAME == 'master' || env.BRANCH_NAME.startsWith("hotfix")
}