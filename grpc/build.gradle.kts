plugins {
    id("io.quarkus")
    id("org.sonarqube")

    kotlin("jvm")
    kotlin("plugin.allopen")
}

val assertJVersion: String by project
val dataFakerVersion: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(project(":library"))

    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-grpc")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-jacoco")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core:$assertJVersion")

    testImplementation("net.datafaker:datafaker:$dataFakerVersion")
}
