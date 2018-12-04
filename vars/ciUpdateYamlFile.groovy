def call(Map config) {
    yaml = readYaml file: config.yamlFile
    config.params.each{ k, v -> yaml."${k}"=v}
    sh "rm -f ${config.yamlFile}"
    writeYaml file: config.yamlFile, data: yaml
}