val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    // buildSrc in combination with this plugin ensures that the version set here
    // will be set to the same for all other Kotlin dependencies / plugins in the project.
    add("implementation", libs.findLibrary("kotlin-gradle").get())
}