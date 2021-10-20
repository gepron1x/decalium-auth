plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.gepron1x"
version = "1.0"

var libsPackage = "org.gepron1x.libraries"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    compileOnly("net.kyori:adventure-api:4.9.2")
    implementation("com.google.guava:guava:31.0.1-jre")
}

tasks {
    shadowJar {

    }
}

