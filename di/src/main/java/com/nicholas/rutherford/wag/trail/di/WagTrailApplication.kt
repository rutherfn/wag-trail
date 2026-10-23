package com.nicholas.rutherford.wag.trail.di

import android.app.Application
import com.nicholas.rutherford.wag.trail.di.WagTrailGraph
import dev.zacsweers.metro.createGraph

class WagTrailApplication : Application() {
    val graph: WagTrailGraph = createGraph<WagTrailGraph>()
}
