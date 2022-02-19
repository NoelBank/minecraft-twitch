plugins {
    kotlin("jvm") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = 'dev.nbank'
version = '0.0.1'

repositories {
    jcenter()
    mavenCentral()

    maven {
        name = 'spigotmc-repo'
        url = 'https://hub.spigotmc.org/nexus/content/repositories/snapshots/'
    }
    maven {
        name = 'sonatype'
        url = 'https://oss.sonatype.org/content/groups/public/'
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compileOnly 'org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT'
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    processResources {
        filteringCharset = "UTF-8"
        filter<org.apache.tools.ant.filters.ReplaceTokens>(
                "tokens" to mapOf(
                        Pair("name", project.name),
                        Pair("description", "Minecraft Twitch Plugin."),
                        Pair("url", "https://nbank.dev.com"),
                        Pair("package", "${project.group}.${project.name.toLowerCase()}"),
                        Pair("version", project.version)
                )
        )
    }

    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }
}