plugins {
    kotlin("jvm") version "2.2.21"

    id("org.jetbrains.dokka") version "2.1.0"

    `maven-publish`
    signing
    id("com.vanniktech.maven.publish") version "0.35.0"
}

group = "cn.elytra"
version = "1.0.0"

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

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates("cn.elytra", "exposed-object-id", "${project.version}")
    pom {
        name = "Exposed ObjectId Extension"
        description = "Add ObjectId column type and entity types."
        inceptionYear = "2025"
        url = "https://github.com/ElytraServers/Exposed-ObjectId"
        licenses {
            license {
                name = "Apache-2.0 License"
                url = "https://github.com/ElytraServers/Exposed-ObjectId/blob/master/LICENSE"
            }
        }
        developers {
            developer {
                id = "taskeren"
                name = "Taskeren"
                url = "https://github.com/Taskeren"
            }
        }
        scm {
            url = "https://github.com/ElytraServers/Exposed-ObjectId"
            connection = "scm:git:git://github.com/ElytraServers/Exposed-ObjectId.git"
            developerConnection = "scm:git:ssh://github.com/ElytraServers/Exposed-ObjectId.git"
        }
    }
}
