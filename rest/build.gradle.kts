plugins {
    id("io.quarkus")
    id("org.sonarqube")

    kotlin("jvm")
    kotlin("plugin.allopen")
}

val assertJVersion: String by project
val commonLangVersion: String by project
val dataFakerVersion: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(project(":library"))

    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-hibernate-orm-rest-data-panache")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    testImplementation("io.quarkus:quarkus-junit5")

    testImplementation("io.rest-assured:rest-assured")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.assertj:assertj-core:$assertJVersion")

    testImplementation("net.datafaker:datafaker:$dataFakerVersion")

    testImplementation("org.apache.commons:commons-lang3:$commonLangVersion")
}
