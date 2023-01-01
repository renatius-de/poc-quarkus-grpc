plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")

    id("io.quarkus")
}

val quarkusPlatformVersion: String by project
val testContainersPlatformVersion: String by project
val assertJVersion: String by project

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-flyway")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-jacoco")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-kotlin")
    testImplementation("io.quarkus:quarkus-junit5")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation(enforcedPlatform("org.testcontainers:testcontainers-bom:${testContainersPlatformVersion}"))
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:testcontainers")

    testImplementation("org.assertj:assertj-core:${assertJVersion}")
}
