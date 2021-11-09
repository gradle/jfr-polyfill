plugins {
    groovy
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    testImplementation(project(":jfr-polyfill"))

    testImplementation("org.codehaus.groovy:groovy:3.0.9")

    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0")
}

for (javaVersion in 8..17) {
    tasks.register<Test>("testsOn$javaVersion") {
        javaLauncher.set(javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        })
        systemProperty("polyfill.expected", javaVersion in 9..10)
    }
}

tasks.withType<Test>().configureEach {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

