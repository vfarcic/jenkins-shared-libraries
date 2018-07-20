def call(project, address) {
    sh """oc -n ${project} \
        create route edge \
        --service ${project} \
        --insecure-policy Allow \
        --hostname ${address}"""
}