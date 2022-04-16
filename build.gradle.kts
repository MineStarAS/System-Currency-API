plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

group = "kr.kro.minestar"
version = "1.0.1"

val plugins = File("C:\\Users\\MineStar\\Desktop\\MC Server folder\\libs")


repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
    maven("https://repo.projecttl.net/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("net.kyori:adventure-api:4.10.1")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")

    implementation("com.arcaniax:HeadDatabase-API:1.3.1")

    //project_TL

    //MineStar
//    compileOnly("kr.kro.minestar:NMS:1.0.0")
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
        create<MavenPublication>("${rootProject.name}-api") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://repo.projecttl.net/repository/maven-releases/"
                    val snapshotsRepoUrl = "https://repo.projecttl.net/repository/maven-snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        username = project.properties["username"] as String?
                        password = project.properties["password"] as String?
                    }
                }

                pom {
                    val repository = rootProject.name
                    name.set(repository)
                    description.set("This is MineStar's $repository plugin")
                    url.set("https://github.com/MineStarAS/$repository")
                    licenses {
                        license {
                            name.set("GNU GENERAL PUBLIC LICENSE Version 3")
                            url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("MineStarAS")
                            name.set("MineStar")
                            email.set("band1019@naver.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/MineStarAS/$repository.git")
                        developerConnection.set("scm:git:https://github.com/MineStarAS/$repository.git")
                        url.set("https://github.com/MineStarAS/$repository.git")
                    }
                }
            }
        }
    }
}

