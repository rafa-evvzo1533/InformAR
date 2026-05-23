plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.ksp)
    id("com.android.library")
    id("androidx.room")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }
    
    // Configuración de XCFramework para iOS
    val xcf = org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework("Shared")

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
            
            implementation(libs.androidx.room.runtime)
            implementation("androidx.sqlite:sqlite-bundled:2.5.0-alpha01")
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
        }
        iosMain.dependencies {}
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspCommonMainMetadata", libs.androidx.room.compiler)
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}

android {
    namespace = "com.alvear.informar.shared"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
}
