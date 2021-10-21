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
    implementation(project(":auth-api"))
    compileOnly("net.kyori:adventure-api:4.9.2")
    implementation ("net.kyori:adventure-text-minimessage:4.2.0-SNAPSHOT") {
        exclude(group = "net.kyori", module = "adventure-api")
    }
    implementation("com.zaxxer:HikariCP:5.0.0")
    implementation("org.jdbi:jdbi3-core:3.23.0")
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.2.1") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
    implementation("net.kyori:event-api:5.0.0-SNAPSHOT")
    implementation("cloud.commandframework:cloud-core:1.5.0")
}

tasks {
    shadowJar {
        relocate("com.zaxxer.hikari", "$libsPackage.hikari")
        relocate("org.jdbi", "$libsPackage.jdbi")
        relocate("cloud.commandframework", "$libsPackage.cloud.commandframework")
        relocate("space.arim.dazzleconf", "$libsPackage.dazzleconf")
        relocate("net.kyori.adventure.text.minimessage", "$libsPackage.minimessage")
        relocate("com.github.benmanes.caffeine", "$libsPackage.caffeine")
        relocate("io.leangen.geantyref", "$libsPackage.geantyref")
    }
}

