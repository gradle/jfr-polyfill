plugins {
    id("com.gradle.enterprise") version "3.7.1"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "jfr-polyfill-parent"
include("jfr-polyfill")
include("specs")
