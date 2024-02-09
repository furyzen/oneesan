import fr.furyzen.gradle.constant.JDK_VERSION

plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JDK_VERSION))
    }
}

tasks.compileJava {
    // See: https://docs.oracle.com/en/java/javase/12/tools/javac.html
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all", // Enables all recommended warnings.
        )
    )
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

repositories {
    mavenCentral()
}