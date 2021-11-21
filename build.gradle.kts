plugins {
    kotlin("jvm") version "1.5.10"
    java
}

group = "me.giverplay"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.processResources {
    val props = hashMapOf("version" to version)
    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(props)
    }
}