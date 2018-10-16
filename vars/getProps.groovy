def call() {
  def props = [
    registry: "docker.acme.com",
    org: env.GIT_URL.tokenize('/')[3],
    repo: env.GIT_URL.tokenize('/')[4]
  ]

  return props
}
