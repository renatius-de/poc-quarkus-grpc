pluginManagement {
    val kotlinVersion: String by settings
    val quarkusPlatformVersion: String by settings
    val sonarQubeVersion: String by settings

    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion

        id("io.quarkus") version quarkusPlatformVersion
        id("org.sonarqube") version sonarQubeVersion
    }
}

rootProject.name = "quarkus"

include("library")
include("rest")
