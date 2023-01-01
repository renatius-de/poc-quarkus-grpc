plugins {
    id("idea")
    id("eclipse")

    kotlin("jvm") version "1.7.21"
    kotlin("plugin.allopen") version "1.7.21"

    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformVersion: String by project
val testContainersPlatformVersion: String by project

group = "de.renatius.poc"
version = "0.1-SNAPSHOT"

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))
    testImplementation(enforcedPlatform("org.testcontainers:testcontainers-bom:${testContainersPlatformVersion}"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}
