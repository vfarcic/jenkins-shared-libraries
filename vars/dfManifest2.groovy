def call(String project) {
    // Download manifest-tool
    sh """bash -c "
        curl -L -o manifest-tool https://github.com/estesp/manifest-tool/releases/download/v0.7.0/manifest-tool-linux-amd64
        chmod +x manifest-tool"
    """
    
    // Login docker
    dfLogin()
    
    // Create and push manifest list
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:${currentBuild.displayName}"
    sh "./manifest-tool push from-args --platforms linux/amd64,linux/arm --template dockerflow/${project}:${currentBuild.displayName}-OS-ARCH --target dockerflow/${project}:latest"
    
    // Logout docker
    dockerLogout()
}
