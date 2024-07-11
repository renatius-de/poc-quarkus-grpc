import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    eclipse
    idea
    jacoco
    java

    id("org.sonarqube")

    kotlin("jvm")
}

group = "de.renatius.poc"

allprojects {
    version = "0.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
        }
    }

    tasks.withType<Test> {
        finalizedBy(tasks.jacocoTestReport)

        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

        testLogging {
            events("passed", "skipped", "failed")
        }

        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        kotlinOptions.javaParameters = true
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "renatius-de_poc-quarkus")
        property("sonar.organization", "renatius-de")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

subprojects {
    group = "de.renatius.poc.quarkus"
}
