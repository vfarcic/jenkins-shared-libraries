def call(project) {
    sh """helm rollback \
        ${project} 0 \
        --tiller-namespace ${project}-build"""
    error "Failed production tests"
}