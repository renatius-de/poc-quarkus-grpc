plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")

    id("io.quarkus")
}

val assertJVersion: String by project
val dataFakerVersion: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-flyway")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-hibernate-orm-rest-data-panache")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-jacoco")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-spring-data-jpa")
    testImplementation("io.quarkus:quarkus-junit5")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core:${assertJVersion}")

    testImplementation("net.datafaker:datafaker:${dataFakerVersion}")
}
