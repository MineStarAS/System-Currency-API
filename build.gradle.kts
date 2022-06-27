plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

group = "kr.kro.minestar"
version = "1.0.0"

val plugins = File("C:\\Users\\MineStar\\Desktop\\MC Server folder\\libs")


repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
    maven(url = "https://jitpack.io/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("net.kyori:adventure-api:4.11.0")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")

//    compileOnly("com.github.MineStarAS:Utility-API:1.2.6")
//    compileOnly(files("C:\\Users\\MineStar\\Desktop\\MC Server folder\\MCserver 1.18.1 - vanilla\\plugins\\MineStar-Currency-1.0.0.jar"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    javadoc {
        options.encoding = "UTF-8"
    }
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")
        from("$buildDir/dokka/html")
    }
    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
        archivePath.delete()
        doLast {
            // jar file copy
            copy {
                from(archiveFile)
                into(if (File(plugins, archiveFileName.get()).exists()) plugins else plugins)
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifact(tasks["sourcesJar"])
            from(components["java"])
        }
    }
}

