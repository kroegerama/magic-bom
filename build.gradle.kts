@file:Suppress("SpellCheckingInspection")

group = C.PROJECT_GROUP_ID
version = C.PROJECT_VERSION

plugins {
    `java-platform`
    `maven-publish`
    signing
    id("io.codearte.nexus-staging")
}

dependencies {
    constraints {
        val kotlin = "1.4.30"
        val kotlinCoroutines = "1.4.2"
        val androidGradlePlugin = "4.1.2"
        val androidHiltPlugin = "2.32-alpha"
        val desugarLibs = "1.1.1"

        val xActivity = "1.2.0"
        val xAppcompat = "1.2.0"
        val xBiometric = "1.1.0-rc01"
        val xBrowser = "1.3.0"
        val xConstraint = "2.0.4"
        val xCore = "1.3.2"
        val xExif = "1.3.2"
        val xFragment = "1.3.0"
        val xHilt = "1.0.0-alpha03"
        val xLifecycle = "2.3.0"
        val xNavigation = "2.3.3"
        val xPaging = "2.1.2"
        val xPreference = "1.1.1"
        val xRecycler = "1.2.0-beta01"
        val xRoom = "2.2.6"
        val xSwiperefresh = "1.1.0"
        val xWork = "2.5.0"

        val material = "1.3.0"
        val timber = "4.7.1"
        val coil = "1.1.1"

        val squareMoshi = "1.11.0"
        val squareOkHttp = "4.9.0"
        val squareRetrofit = "2.9.0"

        val chucker = "3.4.0"

        //plugins
        api(kotlin("gradle-plugin", version = kotlin))
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutines")
        api("com.android.tools.build:gradle:$androidGradlePlugin")
        api("com.google.dagger:hilt-android-gradle-plugin:$androidHiltPlugin")
        api("com.google.dagger:hilt-android:$androidHiltPlugin")
        api("com.google.dagger:hilt-compiler:$androidHiltPlugin")

        api("com.android.tools:desugar_jdk_libs:$desugarLibs")

        //androidX
        api("androidx.activity:activity:$xActivity")
        api("androidx.activity:activity-ktx:$xActivity")
        api("androidx.appcompat:appcompat:$xAppcompat")
        api("androidx.biometric:biometric:$xBiometric")
        api("androidx.browser:browser:$xBrowser")
        api("androidx.constraintlayout:constraintlayout:$xConstraint")
        api("androidx.core:core:$xCore")
        api("androidx.core:core-ktx:$xCore")
        api("androidx.exifinterface:exifinterface:$xExif")
        api("androidx.fragment:fragment:$xFragment")
        api("androidx.fragment:fragment-ktx:$xFragment")

        api("androidx.hilt:hilt-lifecycle-viewmodel:$xHilt")
        api("androidx.hilt:hilt-work:$xHilt")
        api("androidx.hilt:hilt-compiler:$xHilt")

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
                val nexusUsername: String by project
                val nexusPassword: String by project

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

nexusStaging {
    val nexusUsername: String by project
    val nexusPassword: String by project
    val nexusStagingProfileId: String by project

    packageGroup = C.PROJECT_GROUP_ID
    stagingProfileId = nexusStagingProfileId
    username = nexusUsername
    password = nexusPassword
}