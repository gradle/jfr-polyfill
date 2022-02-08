import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

version = "2021.2"

project {
    buildType{
        name = "Publish JFR Polyfill"
        id = RelativeId(name.toId())
        description = "Publish JFR Polyfill to Maven Central staging repository"

        vcs {
            root(AbsoluteId("OpenSourceProjects_JfrPolyfill_HttpsGithubComGradleJfrPolyfillGitRefsHeadsMaster"))
            checkoutMode = CheckoutMode.ON_AGENT
            cleanCheckout = true
        }

        requirements {
            contains("teamcity.agent.jvm.os.name", "Linux")
        }

        steps {
            gradle {
                useGradleWrapper = true
                tasks = "clean publishMavenJavaPublicationToSonatypeRepository"
                gradleParams = "--build-cache"
                buildFile = "build.gradle.kts"
            }
        }
        params {
            param("env.ORG_GRADLE_PROJECT_sonatypeUsername", "%mavenCentralStagingRepoUser%")
            password("env.ORG_GRADLE_PROJECT_sonatypePassword", "%mavenCentralStagingRepoPassword%")
        }
    }
    params {
        param("env.GRADLE_ENTERPRISE_ACCESS_KEY", "%ge.gradle.org.access.key%")
        param("env.GRADLE_CACHE_REMOTE_URL", "%gradle.cache.remote.url%")
        param("env.GRADLE_CACHE_REMOTE_USERNAME", "%gradle.cache.remote.username%")
        password("env.GRADLE_CACHE_REMOTE_PASSWORD", "%gradle.cache.remote.password%")
    }
}
