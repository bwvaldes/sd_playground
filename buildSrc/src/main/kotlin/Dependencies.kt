private object Versions {
    /* AndroidX */
    const val androidCore = "1.3.2"
    const val androidAppCompat = "1.2.0"
    const val androidLegacy = "1.0.0"
    const val androidPreference = "1.1.1"
    const val androidLifeCycle = "2.3.0"
    const val androidNavigation = "2.3.3"
    const val androidJunit = "1.1.2"
    const val androidEspresso = "3.3.0"
    const val androidConstraintLayout = "2.0.4"
    const val androidVectorDrawable = "1.1.0"

    /* App */
    const val gradle = "7.0.0-alpha07"
    const val kotlin = "1.3.61"
    const val navigation = "2.3.3"

    /* UI */
    const val materialDesign = "1.3.0"

    /* Testing */
    const val junit = "4.13.1"
}

object PluginVersions {
    const val ktlint = "10.0.0"
    const val kotlinter = "3.3.0"
    const val spotless = "5.10.2"
}

object AppConfig {
    const val compileSdk = 30
    const val minSdk = 16
    const val targetSdk = 30
    const val buildTools = "30.0.2"
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.scubadeving.sd_playground"
}

object AppClassPaths {
    private const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    private const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    private const val navArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    val classPaths = listOf(
        gradle,
        kotlin,
        navArgs
    )
}

object AndroidXLibs {
    private const val core = "androidx.core:core-ktx:${Versions.androidCore}"
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
    private const val legacy = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
    private const val preference = "androidx.preference:preference-ktx:${Versions.androidPreference}"
    private const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifeCycle}"
    private const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifeCycle}"
    private const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
    private const val ui ="androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
    private const val constraintLayout ="androidx.constraintlayout:constraintlayout:${Versions.androidConstraintLayout}"
    private const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.androidVectorDrawable}"

    val implementations = listOf(
        core, appCompat, legacy, preference, livedata, viewmodel, fragment, ui, constraintLayout, vectorDrawable
    )
}

object KotlinLibs {
    private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    val implementations = listOf(kotlin)
}

object ThirdPartyLibs {
    private const val material = "com.google.android.material:material:${Versions.materialDesign}"

    val implementations = listOf(material)
}

object TestLibs {
    private const val junit = "junit:junit:${Versions.junit}"
    private const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"

    val testImplementations = listOf(junit)
    val androidTestImplementations = listOf(androidJunit, espresso)
}
