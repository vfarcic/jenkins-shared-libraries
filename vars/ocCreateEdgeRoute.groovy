def call(project, address) {
    try { // Needs to be created only once
        sh """oc -n ${project} \
            create route edge \
            --service ${project} \
            --insecure-policy Allow \
            --hostname ${address}"""
    }
}