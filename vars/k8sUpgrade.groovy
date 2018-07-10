def call(String chart, String tag, string address) {
    sh """helm upgrade \
        ${chart} \
        helm/go-demo-3 -i \
        --tiller-namespace go-demo-3-build \
        --set image.tag=${tag} \
        --set ingress.host=${address}"""
}