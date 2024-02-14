import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.papermc.paperweight.tasks.RemapJar
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("java-conventions")
    id("io.papermc.paperweight.userdev") version "1.5.11"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.2"
    id("net.kyori.blossom") version "2.1.0"
}

dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    compileOnly(project.libs.packetevents)
}

tasks {
    named<RunServer>("runServer") {
        minecraftVersion("1.20.4")

        downloadPlugins {
            // Required dependencies
            github("retrooper", "packetevents", "v2.2.1", "packetevents-spigot-2.2.1.jar")

            // Optional dependencies
            hangar("ViaVersion", "4.9.2")
            hangar("ViaBackwards", "4.9.1")
            hangar("ViaRewind", "3.0.5")
        }
    }
    named<ShadowJar>("shadowJar") {
        dependencies {}
        minimize()

        archiveBaseName.set("${rootProject.name}-${project.name}")
    }
    named<RemapJar>("reobfJar") {
        outputJar.set(layout.buildDirectory.file("libs/${rootProject.name}-${project.name}-${project.version}.jar"))
    }
    named("build") {
        dependsOn(shadowJar)
        dependsOn(reobfJar)
    }
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
            }
            resources {
                property("version", project.version.toString())
            }
        }
    }
}