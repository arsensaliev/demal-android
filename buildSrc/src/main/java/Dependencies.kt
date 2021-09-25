import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.demal"
    const val compileSdk = 30
    const val minSdk = 24
    const val targetSdk = 30
    const val kotlinVersion = "1.5.30"
    const val buildTools = "30.0.3"
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    //Tools
    const val multidex = "2.0.1"

    //Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.0"
    const val viewPager2 = "1.0.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.30"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.1"

    //Cicerone
    const val cicerone = "6.6"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinCore = "2.2.3"
    const val koinAndroid = "2.2.3"
    const val koinViewModel = "2.2.3"

    //Glide
    const val glide = "4.9.0"
    const val glideCompiler = "4.9.0"

    //AwesomeValidation
    const val validation = "4.3"

//    May be useful later
//    Room
//    const val roomKtx = "2.2.0-alpha01"
//    const val runtime = "2.2.0-alpha01"
//    const val roomCompiler = "2.2.0-alpha01"

    //Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"

    //CircleIndicator
    const val circleIndicator = "2.1.6"
}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val circleIndicator = "me.relex:circleindicator:${Versions.circleIndicator}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapterCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koinViewModel = "io.insert-koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

/*object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}*/

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object AwesomeValidation {
    const val validator = "com.basgeekball:awesome-validation:${Versions.validation}"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val featProfile = ":feature-profile"
    const val featProfileEdit = ":feature-profile-edit"
    const val featTours = ":feature-tours"
    const val featHome = ":feature-home"
    const val featWishlist = ":feature-wishlist"
    const val featLogin = ":feature-login"
    const val featOnboarding = ":feature-onboarding"
    const val featMyTours = ":feature-mytours"
    const val featTour = ":feature-tour"
    const val featRegister= ":feature-register"
}

object Cicerone {
    const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
}