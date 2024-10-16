object Testing {
    private const val junitVersion = "4.13.2"
    const val junit4 = "junit:junit:$junitVersion"

    private const val junitAndroidExtVersion = "1.2.1"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.9.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    private const val truthVersion = "1.4.4"
    const val truth = "com.google.truth:truth:$truthVersion"

    private const val mockkVersion = "1.13.13"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    private const val mockWebServerVersion = "4.12.0"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

    private const val testRunnerVersion = "1.6.2"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    private const val robolectricVersion = "4.13"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

    const val espressoCore = "androidx.test.espresso:espresso-core:3.6.1"
}