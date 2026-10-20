package com.nicholas.rutherford.wag.trail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.nicholas.rutherford.wag.trail.base.vm.LocalViewModelFactory
import com.nicholas.rutherford.wag.trail.feature.dogslist.DogsListScreen
import com.nicholas.rutherford.wag.trail.ui.theme.WagTrailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelFactory = (application as WagTrailApplication).graph.viewModelFactory
        setContent {
            CompositionLocalProvider(value = LocalViewModelFactory provides viewModelFactory) {
                WagTrailTheme {
                    DogsListScreen()
                }
            }
        }
    }
}
