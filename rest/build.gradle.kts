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
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    testImplementation("io.quarkus:quarkus-junit5")

    testImplementation("io.rest-assured:rest-assured")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core:$assertJVersion")

    testImplementation("net.datafaker:datafaker:$dataFakerVersion")
}
