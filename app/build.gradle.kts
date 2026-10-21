// Configuration for the one and only app module.
plugins {
    id("com.android.application")
}

android {
    namespace = "com.mad.budgettracker"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.mad.budgettracker"
        // 26 = Android 8.0. Lets us use an XML adaptive launcher icon with no PNGs.
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // AppCompatActivity, the base class every screen extends
    implementation("androidx.appcompat:appcompat:1.7.1")
    // Material Components: MaterialButton, MaterialCardView, TextInputLayout, themes
    implementation("com.google.android.material:material:1.13.0")
    // ConstraintLayout for positioning views in XML
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
}
