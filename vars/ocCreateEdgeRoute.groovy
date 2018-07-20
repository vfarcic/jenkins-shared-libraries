def call(project, address) {
    try {
        sh """oc -n ${project} \
            create route edge \
            --service ${project} \
            --insecure-policy Allow \
            --hostname ${address}"""
    } catch(e) {
    } finally {
        echo "Don't panic! The route already exists so it cannot be recreated."
    }
}