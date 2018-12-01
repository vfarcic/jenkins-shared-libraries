def call(Map config) {
    sh "git config user.email ${config?.email ?: 'jenkins@example.com'} && git config user.name ${config?.username ?: 'Jenkins'}"

    sh "git tag ${config.tag} ${env.GIT_COMMIT}"
    sh "git push origin ${config.tag} "

}