def call() {
    def escapedBranch = env.BRANCH_NAME
            .toString()
            .toLowerCase()
            .replace("/", "-")

    return escapedBranch
}