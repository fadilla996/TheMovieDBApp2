package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidX() {
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
}