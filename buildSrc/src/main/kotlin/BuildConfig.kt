import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Action
import org.gradle.api.publish.maven.MavenPom
import org.gradle.kotlin.dsl.delegateClosureOf

object BuildConfig {

    val pomAction = Action<MavenPom> {
        with(C) {
            name.set(PROJECT_NAME)
            description.set(PROJECT_DESCRIPTION)
            url.set(PROJECT_URL)

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set(USER_ID)
                    name.set(USER_NAME)
                    email.set(USER_MAIL)
                }
            }
            scm {
                url.set(PROJECT_URL)
                connection.set("scm:git:$PROJECT_URL")
                developerConnection.set("scm:git:https://www.github.com/kroegerama")
            }
        }
    }

    val pkgConfig = delegateClosureOf<BintrayExtension.PackageConfig> {
        with(C) {
            repo = "maven"
            userOrg = USER_ID

            name = "magic-bom"
            desc = PROJECT_DESCRIPTION

            setLicenses("Apache-2.0")
            vcsUrl = PROJECT_URL
            setLabels(*PROJECT_TAGS)
            publicDownloadNumbers = true
        }
    }
}