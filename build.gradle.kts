@file:Suppress("SpellCheckingInspection")

group = C.PROJECT_GROUP_ID
version = C.PROJECT_VERSION

plugins {
    `java-platform`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
}

dependencies {
    constraints {
        val kotlin = "1.5.31"
        val kotlinCoroutines = "1.5.2"
        val androidGradlePlugin = "7.0.2"
        val androidHiltPlugin = "2.38.1"
        val desugarLibs = "1.1.5"
        val serializationJson = "1.3.0"

        val xActivity = "1.3.1"
        val xAppcompat = "1.3.1"
        val xAutofill = "1.1.0"
        val xBiometric = "1.1.0"
        val xBrowser = "1.3.0"
        val xConstraint = "2.1.1"
        val xCore = "1.6.0"
        val xExif = "1.3.3"
        val xFragment = "1.3.6"
        val xHilt = "1.0.0"
        val xLifecycle = "2.4.0-beta01"
        val xNavigation = "2.3.5"
        val xPaging = "3.0.1"
        val xPreference = "1.1.1"
        val xRecycler = "1.2.1"
        val xRoom = "2.3.0"
        val xSwiperefresh = "1.1.0"
        val xWork = "2.6.0"

        val material = "1.4.0"
        val timber = "5.0.1"
        val coil = "1.3.2"

        val squareMoshi = "1.12.0"
        val squareOkHttp = "4.9.1"
        val squareRetrofit = "2.9.0"

        val chucker = "3.5.2"

        //plugins
        api(kotlin("gradle-plugin", version = kotlin))
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutines")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutines")
        api("com.android.tools.build:gradle:$androidGradlePlugin")
        api("com.google.dagger:hilt-android-gradle-plugin:$androidHiltPlugin")
        api("com.google.dagger:hilt-android:$androidHiltPlugin")
        api("com.google.dagger:hilt-compiler:$androidHiltPlugin")

        api("com.android.tools:desugar_jdk_libs:$desugarLibs")

        api(kotlin("plugin.serialization", version = kotlin))
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJson")

        //androidX
        api("androidx.activity:activity:$xActivity")
        api("androidx.activity:activity-ktx:$xActivity")
        api("androidx.appcompat:appcompat:$xAppcompat")
        api("androidx.autofill:autofill:$xAutofill")
        api("androidx.biometric:biometric:$xBiometric")
        api("androidx.browser:browser:$xBrowser")
        api("androidx.constraintlayout:constraintlayout:$xConstraint")
        api("androidx.core:core:$xCore")
        api("androidx.core:core-ktx:$xCore")
        api("androidx.exifinterface:exifinterface:$xExif")
        api("androidx.fragment:fragment:$xFragment")
        api("androidx.fragment:fragment-ktx:$xFragment")

        api("androidx.hilt:hilt-common:$xHilt")
        api("androidx.hilt:hilt-compiler:$xHilt")
        api("androidx.hilt:hilt-navigation:$xHilt")
        api("androidx.hilt:hilt-navigation-fragment:$xHilt")
        api("androidx.hilt:hilt-work:$xHilt")

        api("androidx.lifecycle:lifecycle-viewmodel-ktx:$xLifecycle")
        api("androidx.lifecycle:lifecycle-livedata-ktx:$xLifecycle")
        api("androidx.lifecycle:lifecycle-runtime-ktx:$xLifecycle")
        api("androidx.lifecycle:lifecycle-common-java8:$xLifecycle")
        api("androidx.lifecycle:lifecycle-process:$xLifecycle")
        api("androidx.lifecycle:lifecycle-service:$xLifecycle")
        api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$xLifecycle")

        api("androidx.navigation:navigation-common:$xNavigation")
        api("androidx.navigation:navigation-fragment:$xNavigation")
        api("androidx.navigation:navigation-fragment-ktx:$xNavigation")
        api("androidx.navigation:navigation-ui:$xNavigation")
        api("androidx.navigation:navigation-ui-ktx:$xNavigation")
        api("androidx.navigation:navigation-safe-args-gradle-plugin:$xNavigation")

        api("androidx.paging:paging-runtime:$xPaging")
        api("androidx.paging:paging-runtime-ktx:$xPaging")

        api("androidx.preference:preference:$xPreference")
        api("androidx.preference:preference-ktx:$xPreference")
        api("androidx.recyclerview:recyclerview:$xRecycler")

        api("androidx.room:room-runtime:$xRoom")
        api("androidx.room:room-compiler:$xRoom")
        api("androidx.room:room-ktx:$xRoom")

        api("androidx.swiperefreshlayout:swiperefreshlayout:$xSwiperefresh")

        api("androidx.work:work-runtime:$xWork")
        api("androidx.work:work-runtime-ktx:$xWork")

        //square
        api("com.squareup.moshi:moshi:$squareMoshi")
        api("com.squareup.moshi:moshi-adapters:$squareMoshi")
        api("com.squareup.moshi:moshi-kotlin-codegen:$squareMoshi")
        api("com.squareup.okhttp3:okhttp:$squareOkHttp")
        api("com.squareup.okhttp3:logging-interceptor:$squareOkHttp")
        api("com.squareup.retrofit2:retrofit:$squareRetrofit")

        //libraries
        api("com.google.android.material:material:$material")
        api("com.jakewharton.timber:timber:$timber")

        api("io.coil-kt:coil:$coil")

        //utils
        api("com.github.chuckerteam.chucker:library:$chucker")
        api("com.github.chuckerteam.chucker:library-no-op:$chucker")

    }
}

//fun property(propName: String, envName: String = propName): String? =
//    findProperty(name)?.toString() ?: System.getenv(envName)

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = C.PROJECT_GROUP_ID
            artifactId = C.PROJECT_ARTIFACT_ID
            version = C.PROJECT_VERSION

            from(components["javaPlatform"])
            pom(BuildConfig.pomAction)
        }
    }
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                val nexusUsername: String? by project
                val nexusPassword: String? by project

                username = nexusUsername
                password = nexusPassword
            }
        }
    }
}

signing {
    sign(publishing.publications)
    val signingKey: String? by project
    val signingPassword: String? by project
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}

nexusPublishing {
    val nexusUsername: String? by project
    val nexusPassword: String? by project
    val nexusStagingProfileId: String? by project

    packageGroup.set(C.PROJECT_GROUP_ID)

    repositories {
        sonatype {
            stagingProfileId.set(nexusStagingProfileId)
            username.set(nexusUsername)
            password.set(nexusPassword)
        }
    }
}
