group = "com.mbiamont"
version = "0.1.0"

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath(kotlin("gradle-plugin", version = "1.6.10"))
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    plugins.withId("com.vanniktech.maven.publish") {
        extensions.getByType<com.vanniktech.maven.publish.MavenPublishPluginExtension>().apply {
            sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
        }
    }
}