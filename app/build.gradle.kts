plugins {
    id("kotlin-android-extensions")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "za.co.lbnkosi.portfolio"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures { dataBinding = true }

    lintOptions { isCheckReleaseBuilds = false }

    hilt { enableExperimentalClasspathAggregation = true }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-native-mt")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    implementation("androidx.core:core-ktx:1.7.0")

    //Android
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    //Lottie
    implementation("com.airbnb.android:lottie:3.6.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    //Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Arch Components
    implementation("androidx.lifecycle:lifecycle-common:2.4.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    //Room - https://developer.android.com/jetpack/androidx/releases/room
    kapt("androidx.room:room-compiler:2.4.1") //Kapt
    implementation("androidx.room:room-ktx:2.4.1")
    implementation("androidx.room:room-runtime:2.4.1")
    testImplementation("androidx.room:room-testing:2.4.1") //testImplementation

    //Stetho - https://github.com/facebookarchive/stetho
    implementation("com.facebook.stetho:stetho:1.6.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")

    //StreamSupport - https://github.com/stefan-zobel/streamsupport
    implementation("net.sourceforge.streamsupport:streamsupport:1.7.3")

    //Glide - https://github.com/bumptech/glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0") //annotationProcessor

    //Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")

    //Multidex
    implementation("androidx.multidex:multidex:2.0.1")

    implementation("com.amitshekhar.android:debug-db:1.0.6")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:29.1.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.0-native-mt")

    implementation("com.google.dagger:hilt-android:2.40.5")
    implementation("com.google.dagger:hilt-compiler:2.40.5")
    implementation("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    testImplementation("junit:junit:")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation("org.robolectric:robolectric:4.7.3")


    testImplementation("com.google.truth:truth:1.1.3")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.38.1")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.38.1")
    // ...with Java.
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.38.1")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.38.1")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.38.1")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.38.1")

    debugImplementation ("com.amitshekhar.android:debug-db:1.0.6")
}