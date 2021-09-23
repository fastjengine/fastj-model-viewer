/*
 * FastJ Kotlin Template Build Script
 *
 * Thank you for choosing FastJ! This is a Gradle-based build script that will help you manage your
 * project with ease. */

plugins {
    /* To begin with, Gradle needs the "kotlin" plugin so that it knows this is a Kotlin project. */
    kotlin("jvm") version "1.5.20"
    /* This template is for an application -- we"ll need this plugin to make sure Gradle knows
     * this, too. */
    application
    /* To create distributable files for your game, we use this runtime plugin. */
    id("org.beryx.runtime") version "1.12.5"
}

/* Your project"s group name goes here.
 * This should be a domain name you own.
 * If you don"t own a domain, don"t worry! You can always set this to "io.github.yourgithubusername". */
group = "io.github.yourgithubusername"

/* Your project"s version.
 * When you want to distribute a new version of your project, always make sure to update the
 * project version. */
version = "0.0.1"

/* Your project"s description.
 * Give a one or two sentence description of your project -- if you publish it somewhere, you"ll be
 * able to use it. */
description = "A Java Template for FastJ."

/* Here, we specify where the main entrypoint of the project.
 * Feel free to change this as needed. */
application.mainClass.set("tech.fastj.template.GameKt")


/* When you add a dependency on another project (like FastJ), you need to add specify where the
 * dependencies are coming from!
 * FastJ is hosted on Maven Central and Jitpack.io, so we"ll add the jitpack.io dependency here. */
repositories.maven {
    setUrl("https://jitpack.io/")
}
repositories.mavenCentral()

/* The dependency for FastJ, the game engine this template depends on. */
dependencies.implementation("com.github.fastjengine:FastJ:1.5.0")

/* To make Kotlin compile and run properly with Gradle, this adds your Kotlin code to the Java
 * source sets. */
sourceSets.main {
    java.srcDirs("src/main/myJava", "src/main/myKotlin")
}


/* The Runtime plugin is used to configure the executables and other distributions for your
 * project. */
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
        /* Use this to define the path of the icons for your project. */
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

