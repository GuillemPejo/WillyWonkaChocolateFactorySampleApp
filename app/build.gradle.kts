plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.plugin.serialization")
}

commonAndroidConfig()

android {
    defaultConfig {
        applicationId = AndroidSdk.applicationId

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
    setupCompose()

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    modules(listOf(Modules.Core, Modules.Libs.CommonsUi))

    implementations(DependencyGroups.diUi)
    implementations(DependencyGroups.network)

    implementation(Dependencies.SupportLibs.splashScreen)
    implementation(Dependencies.SupportLibs.pagingRuntime)
    implementation(Dependencies.Kotlin.kotlinxSerialization)

    implementation(platform(Dependencies.Compose.bom))
    debugImplementation(Dependencies.Compose.uiTooling)
    implementations(DependencyGroups.compose)
    implementation(Dependencies.Libraries.coil)
    implementation(Dependencies.Libraries.coilCompose)

    testImplementation(Dependencies.Testing.mockk)
    testImplementation(Dependencies.Testing.pagingTest)
    androidTestImplementation(Dependencies.Testing.composeAndroidTest)

    kapt(Dependencies.Database.roomCompiler)
    implementations(DependencyGroups.database)
    testImplementation(Dependencies.Database.roomTest)
    androidTestImplementation(Dependencies.Testing.coroutinesTest)
}
