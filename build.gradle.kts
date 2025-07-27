plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"  // Latest stable Kotlin version
    id("org.jetbrains.intellij") version "1.17.2"     // Using the stable intellij plugin
}

group = "io.github.baddel73.dots"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Java and Kotlin compatibility
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            languageVersion = "1.9"
            apiVersion = "1.9"
        }
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("251.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

intellij {
    version.set("2023.3")
    type.set("IC") // IntelliJ IDEA Community Edition

    plugins.set(listOf(
        "com.intellij.java",
        "org.jetbrains.kotlin"
    ))
}