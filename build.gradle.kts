import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "io.github.baddel73.dots"
version = "0.2.2"

repositories {
    mavenCentral()
    maven { url = uri("https://cache-redirector.jetbrains.com/intellij-dependencies") }
    maven { url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies") }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

intellij {
    version.set("2024.1")
    type.set("IC")
    plugins.set(listOf(
        "com.intellij.java",
        "org.jetbrains.kotlin"
    ))
}

sourceSets {
    main {
        java {
            srcDir("src/main/gen")
        }
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        dependsOn("generateLexer", "generateParser")
    }
    withType<JavaCompile>().configureEach {
        dependsOn("generateLexer", "generateParser")
    }

    named("classes") {
        dependsOn("generateLexer", "generateParser")
    }

    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        // Ensure we're using the correct Java version
        options.release.set(17)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("261.*")
    }

    named<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateLexer") {
        sourceFile.set(file("src/main/java/io/github/baddel73/dots/file/DotsLexerGenerator.flex"))
        targetOutputDir.set(file("src/main/gen/io/github/baddel73/dots/language"))
        purgeOldFiles.set(true)
    }

    named<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateParser") {
        sourceFile.set(file("src/main/java/io/github/baddel73/dots/language/DotsLanguageGrammar.bnf"))
        targetRootOutputDir.set(file("src/main/gen"))
        pathToParser.set("io/github/baddel73/dots/language/DotsParser.java")
        pathToPsiRoot.set("io/github/baddel73/dots/language/psi")
        purgeOldFiles.set(true)
    }

    compileJava {
        dependsOn("generateLexer", "generateParser")
    }

    clean {
        delete("src/main/gen")
        doLast {
            file("src/main/gen").deleteRecursively()
        }
    }

    runPluginVerifier {
        ideVersions.set(
            listOf(
                "IC-2024.1",
                "IC-2024.2",
                "IC-2024.3",
                "IC-2025.1",
                "IC-2025.2",
                "IU-2025.2"
            )
        )
    }
}