plugins {
    application
    kotlin("jvm") version "1.5.20"
    id("org.beryx.runtime") version "1.12.5"
}


group = "tech.fastj.fastjengine"
version = "0.0.1"
description = "View FastJ .psdf files."
application.mainClass.set("tech.fastj.template.MainKt")


repositories.maven { setUrl("https://jitpack.io/") }
repositories.mavenCentral()
sourceSets.main { java.srcDirs("src/main/java", "src/main/kotlin") }
dependencies.implementation("com.github.fastjengine:FastJ:1.6.0-SNAPSHOT")


runtime {

    options.addAll(
        "--strip-debug",
        "--compress", "2",
        "--no-header-files",
        "--no-man-pages"
    )

    launcher {
        noConsole = true
    }

    jpackage {
        val iconPath = "project-resources/fastj_icon"
        val currentOs = org.gradle.internal.os.OperatingSystem.current()


        when {
            currentOs.isWindows -> {
                installerType = "msi"
                imageOptions = listOf("--icon", "${iconPath}.ico")
                installerOptions = listOf(
                    "--description", project.description as String,
                    "--vendor", project.group as String,
                    "--app-version", project.version as String,
                    "--win-per-user-install",
                    "--win-dir-chooser",
                    "--win-shortcut",
                )
            }
            currentOs.isLinux -> {
                installerType = "deb"
                imageOptions = listOf("--icon", "${iconPath}.png")
                installerOptions = listOf(
                    "--description", project.description as String,
                    "--vendor", project.group as String,
                    "--app-version", project.version as String,
                    "--linux-shortcut",
                )
            }
            currentOs.isMacOsX -> {
                installerType = "pkg"
                imageOptions = listOf("--icon", "${iconPath}.icns")
                installerOptions = listOf(
                    "--description", project.description as String,
                    "--vendor", project.group as String,
                    "--app-version", project.version as String,
                    "--mac-package-name", project.name
                )
            }
        }
    }
}

