// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.buildGradle)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.safeArgs)
        classpath(Dependencies.googleServices)
        //classpath(Dependencies.crashlytics)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}