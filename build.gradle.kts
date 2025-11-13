plugins {
    kotlin("jvm") version "2.2.20"
}

group = "cn.taskeren"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bson)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
    explicitApi()
}
