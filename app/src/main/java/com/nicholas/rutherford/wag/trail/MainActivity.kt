package com.nicholas.rutherford.wag.trail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.nicholas.rutherford.wag.trail.base.vm.LocalViewModelFactory
import com.nicholas.rutherford.wag.trail.compose.theme.WagTrailTheme
import com.nicholas.rutherford.wag.trail.di.WagTrailApplication
import com.nicholas.rutherford.wag.trail.feature.dogs.dogslist.DogsListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelFactory = (application as WagTrailApplication).graph.viewModelFactory
        setContent {
            CompositionLocalProvider(value = LocalViewModelFactory provides viewModelFactory) {
                WagTrailTheme {
                    val colorScheme = MaterialTheme.colorScheme

                    Scaffold(
                        modifier = Modifier,
                        containerColor = colorScheme.background
                    ) { paddingValues ->
                        DogsListScreen(paddingValues = paddingValues, colorScheme = colorScheme)
                    }
                }
            }
        }
    }
}
