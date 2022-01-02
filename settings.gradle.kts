rootProject.name = "foregroundbottomsheet"

include(
    "app",
    ":library"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenCentral()
        google()
    }
}
