pluginManagement {
    val quarkusPlatformVersion: String by settings

    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }

    plugins {
        id("io.quarkus") version quarkusPlatformVersion
    }
}

rootProject.name = "quarkus"
