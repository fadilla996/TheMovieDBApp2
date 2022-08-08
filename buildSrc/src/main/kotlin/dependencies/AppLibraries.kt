package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appLibraries() {
    androidX()
    materialDesign()
    testImpl()
    daggerHilt()
    gander()
    glide()
    googleFirebase()
    gson()
    materialDesign()
    navGraph()
    okHttp()
    retrofit()
    viewModelLifecycle()
    youtubePlayer()
    androidPaging()
}