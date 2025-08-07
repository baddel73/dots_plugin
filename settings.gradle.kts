rootProject.name = "dots-intellij-plugin"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven { url = uri("https://cache-redirector.jetbrains.com/intellij-dependencies") }
        maven { url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies") }
        maven { url = uri("https://jitpack.io") }
    }
}