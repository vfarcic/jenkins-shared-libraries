import java.text.SimpleDateFormat

def call() {
    currentBuild.displayName = new SimpleDateFormat("yy.MM.dd").format(new Date()) + "-" + env.BUILD_NUMBER
}