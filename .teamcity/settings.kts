import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

version = "2021.1"

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
                gradleParams = "--build-cache -Dgradle.publish.skip.namespace.check=true"
                buildFile = "build.gradle.kts"
            }
        }
        params {
            param("env.MAVEN_CENTRAL_STAGING_REPO_USER", "%mavenCentralStagingRepoUser%")
            password("env.MAVEN_CENTRAL_STAGING_REPO_PASSWORD", "%mavenCentralStagingRepoPassword%")
        }
    }
    params {
        param("env.GRADLE_ENTERPRISE_ACCESS_KEY", "%ge.gradle.org.access.key%")
        param("env.GRADLE_CACHE_REMOTE_URL", "%gradle.cache.remote.url%")
        param("env.GRADLE_CACHE_REMOTE_USERNAME", "%gradle.cache.remote.username%")
        password("env.GRADLE_CACHE_REMOTE_PASSWORD", "%gradle.cache.remote.password%")
    }
}
