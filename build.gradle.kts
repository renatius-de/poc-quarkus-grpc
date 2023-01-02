plugins {
    eclipse
    idea
    jacoco
    java

    kotlin("jvm")
}

group = "de.renatius.poc"

allprojects {
    version = "0.1-SNAPSHOT"

    apply(plugin = "jacoco")
    apply(plugin = "java")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
        }
    }

    tasks.test {
        finalizedBy("jacocoTestReport")
    }

    tasks.withType<Test> {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

        testLogging {
            events("passed", "skipped", "failed")
        }

        useJUnitPlatform()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        kotlinOptions.javaParameters = true
    }
}

subprojects {
    group = "de.renatius.poc.quarkus"
}
