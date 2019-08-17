import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetPreset

plugins {
    kotlin("multiplatform") version "1.3.41"
    id("com.jfrog.bintray") version "1.8.4"
}

repositories {
    mavenCentral()
}

kotlin {
    presets.withType<KotlinNativeTargetPreset>().filter { it.name == "macosX64" || it.name == "linuxX64" }.forEach {
        targetFromPreset(it) {
            compilations.getByName("main") {
                val interop by cinterops.creating {
                    defFile(project.file("src/nativeInterop/cinterop/libpng.def"))
                }

                binaries {
                    executable() {
                        entryPoint = "io.mattmoore.png.main"
                        baseName = "png"
                    }
                }
            }
        }
    }

    sourceSets {
        val macosX64Main by getting {
            kotlin.srcDir("src/nativeMain/kotlin")
        }
        val linuxX64Main by getting {
            kotlin.srcDir("src/nativeMain/kotlin")
        }
    }
}

bintray {
    user = "mattmoore"
    key = "498e6f3347faa4b02f6e8be98a70f6afbf926e96"
    publish = true
//    setPublications("")
    with(pkg) {
        repo = "io.mattmoore"
        name = "png"
//        userOrg = "mattmoore"
        websiteUrl = "https://github.com/mattmoore/kotlin-png"
        vcsUrl = "https://github.com/mattmoore/kotlin-png"
        setLabels("kotlin")
        setLicenses("MIT")
        with(version) {
            name = project.name
            desc = project.description
            vcsTag = project.version.toString()
        }
    }
}
