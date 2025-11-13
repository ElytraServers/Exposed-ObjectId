plugins {
    kotlin("jvm") version "2.2.20"

    `maven-publish`
    id("com.gradleup.nmcp.aggregation") version "1.2.0"
}

group = "cn.elytra"
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

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(8)
    explicitApi()
}

fun prop(name: String): String? = findProperty(name) as? String ?: System.getenv(name)

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

nmcpAggregation {
    centralPortal {
        username = prop("MAVEN_CENTRAL_USERNAME")
        password = prop("MAVEN_CENTRAL_PASSWORD")
        publishingType = "USER_MANAGED"
    }
}
