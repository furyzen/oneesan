import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
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
    withType<Jar> {
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }
    named<RunServer>("runServer") {
        minecraftVersion("1.20.4")

        downloadPlugins {
            github("retrooper", "packetevents", "v2.2.0", "packetevents-spigot-2.2.0.jar")
        }
    }
    named<ShadowJar>("shadowJar") {
        dependencies {
            exclude("net.kyori::")
        }
        minimize()

        archiveBaseName.set("${rootProject.name}-${project.name}")
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