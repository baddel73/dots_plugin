plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "io.github.baddel73.dots"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://cache-redirector.jetbrains.com/intellij-dependencies") }
    maven { url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies") }
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
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("241.*")
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
        purgeOldFiles.set(true)  // Add this line
    }

    compileJava {
        dependsOn("generateLexer", "generateParser")
    }

    clean {
        delete("src/main/gen")
        doLast {
            // Ensure the directory is completely removed
            file("src/main/gen").deleteRecursively()
        }
    }
}