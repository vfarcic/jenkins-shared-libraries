def call(tag) {

    ciWithGitKey {
        //ugly, but could not make it work globally... jenkins sometimes makes me hate my life
        sh """ git config --global user.email "tigran@mna.com" """
        sh """ git config --global user.name "Tigran Mna" """

        sh "git tag $tag ${env.GIT_COMMIT}"
        sh "git push origin $tag "
    }

}