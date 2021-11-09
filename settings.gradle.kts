rootProject.name = "jfr-polyfill-parent"
include("jfr-polyfill")
include("specs")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
