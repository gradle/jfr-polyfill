/*
 * Copyright (c) 2021, Gradle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Gradle Inc., Gradle Inc., 2261 Market St #4081,
 * San FranciscoCA 94114, United States
 * or visit www.gradle.org if you need additional information or have any
 * questions.
 */

import me.champeau.gradle.japicmp.JapicmpTask

plugins {
    `java-library`
    `maven-publish`
    signing
    id("me.champeau.gradle.japicmp") version "0.4.4"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.javadoc {
    (options as StandardJavadocDocletOptions).tags("implNote", "implSpec")
}

tasks {
    val japicmp by registering(JapicmpTask::class) {
        val jdkJfrJar = project.javaToolchains.launcherFor(java.toolchain)
            .map { it.metadata.installationPath.file("jre/lib/jfr.jar") }
        oldClasspath.from(jdkJfrJar)
        newClasspath.from(jar)
        packageIncludes.add("jdk.jfr")
        failOnModification.set(true)
        onlyBinaryIncompatibleModified.set(true)
        htmlOutputFile.set(layout.buildDirectory.file("reports/japi.html"))
        doFirst {
            println("Comparing ${jdkJfrJar.get()} against polyfill")
        }
    }
    check {
        dependsOn(japicmp)
    }
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        artifactId = project.name
        from(components["java"])

        pom {
            name.set("JFR Polyfill")
            description.set("The jfr-polyfill library is a no-op polyfill implementation of the jdk.jfr.* interfaces and classes. The goal is to enable projects to add JFR events to their codebase without dropping support for old JDKs that don’t support JFR.")
            url.set("https://github.com/gradle/jfr-polyfill/")
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
                }
            }
            developers {
                developer {
                    name.set("The Gradle team")
                    organization.set("Gradle Inc.")
                    organizationUrl.set("https://gradle.com")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/gradle/jfr-polyfill.git")
                developerConnection.set("scm:git:ssh://git@github.com:gradle/jfr-polyfill.git")
                url.set("https://github.com/gradle/jfr-polyfill/")
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
    useInMemoryPgpKeys(System.getenv("PGP_SIGNING_KEY"), System.getenv("PGP_SIGNING_KEY_PASSPHRASE"))
}

tasks.withType<Jar>().configureEach {
    into(".") {
        from(rootProject.layout.projectDirectory.file("LICENSE"))
    }
}
