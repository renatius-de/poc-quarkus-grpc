plugins {
    eclipse
    idea
    jacoco

    kotlin("jvm")
    kotlin("plugin.allopen") apply false

    id("io.quarkus") apply false
}

group = "de.renatius.poc"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    version = "0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        kotlinOptions.javaParameters = true
    }

    tasks.withType<Test> {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

        testLogging {
            events("passed", "skipped", "failed")
        }

        useJUnitPlatform()
    }
}

subprojects {
    group = "de.renatius.poc.quarkus"

    apply(plugin = "jacoco")

}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

tasks.test {
    finalizedBy("jacocoTestReport")
}
