package com.nicholas.rutherford.wag.trail.feature.dogs.dogslist

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicholas.rutherford.wag.trail.base.resources.Colors
import com.nicholas.rutherford.wag.trail.base.resources.DrawablesIds
import com.nicholas.rutherford.wag.trail.base.resources.StringsIds
import com.nicholas.rutherford.wag.trail.base.vm.ObserveLifecycle
import com.nicholas.rutherford.wag.trail.base.vm.metroViewModel

// todo documentation
@Composable
fun DogsListScreen(paddingValues: PaddingValues, colorScheme: ColorScheme) {
    val viewModel: DogsListViewModel = metroViewModel()
    val state by viewModel.dogsListStateFlow.collectAsStateWithLifecycle()

    ObserveLifecycle(viewModel = viewModel)
    DogsListScreen(
        paddingValues = paddingValues,
        colorScheme = colorScheme,
        state = state,
        onAddDogClicked = viewModel::onAddDogClicked
    )
}

// todo documentation
@Composable
fun DogsListScreen(
    paddingValues: PaddingValues,
    colorScheme: ColorScheme,
    state: DogsListState,
    onAddDogClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(id = StringsIds.appName),
            modifier = Modifier.padding(top = 8.dp),
            color = colorScheme.primary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.4.sp
        )
        Text(
            text = stringResource(id = StringsIds.dogs),
            modifier = Modifier.padding(top = 6.dp),
            color = colorScheme.onBackground,
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.4).sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (state.dogs.isEmpty()) {
                DogsListEmptyState(
                    onAddDogClicked = onAddDogClicked,
                    modifier = Modifier.padding(bottom = 72.dp)
                )
            } else {
                // dogs list contnet
            }
        }
    }
}

// todo documentation
@Composable
private fun DogsListEmptyState(onAddDogClicked: () -> Unit, modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = DrawablesIds.wagTrailMark),
            contentDescription = null,
            modifier = modifier.size(104.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(id = StringsIds.noDogsYet),
            modifier = Modifier.padding(top = 22.dp),
            color = colors.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.3).sp
        )
        Text(
            text = stringResource(id = StringsIds.addADogDescription),
            modifier = Modifier
                .padding(top = 8.dp)
                .widthIn(max = 230.dp),
            color = colors.onSurfaceVariant,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(28.dp))
        Button(
            onClick = onAddDogClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            )
        ) {
            Text(
                text = stringResource(id = StringsIds.addADog),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DogsListScreenPreview() {
    val colors = if (isSystemInDarkTheme()) {
        darkColorScheme(
            primary = Colors.sage,
            onPrimary = Colors.ink,
            background = Colors.night,
            onBackground = Colors.paper,
            surface = Colors.nightRaised,
            onSurface = Colors.paper,
            onSurfaceVariant = Colors.moss
        )
    } else {
        lightColorScheme(
            primary = Colors.green,
            onPrimary = Colors.cream,
            background = Colors.paper,
            onBackground = Colors.ink,
            surface = Colors.paper,
            onSurface = Colors.ink,
            onSurfaceVariant = Colors.muted
        )
    }

    MaterialTheme(colorScheme = colors) {
        DogsListScreen(
            paddingValues = PaddingValues(),
            colorScheme = MaterialTheme.colorScheme,
            state = DogsListState(),
            onAddDogClicked = {}
        )
    }
}
