package com.example.composeplayground.presentation.explore.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeplayground.presentation.common.composables.CustomAnimatedName
import com.example.composeplayground.presentation.explore.utils.ExploreMainEvent
import com.example.composeplayground.presentation.explore.utils.ExploreMainState
import com.example.composeplayground.presentation.explore.viewModel.ExploreMainViewModel
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun ExploreMainScreen(
    state: ExploreMainState,
    onEvent: (ExploreMainEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onEvent(ExploreMainEvent.ExpandName) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            CustomAnimatedName(name = state.firstName, expanded = state.expanded)

            CustomAnimatedName(name = state.secondName, expanded = state.expanded)
        }

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = {
            onEvent(ExploreMainEvent.ChangeBothNames(firstName = state.secondName, secondName = state.firstName))
        }) {
            Text(text = "Swap")
        }

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = { onEvent(ExploreMainEvent.Pay) }) {
            Text(text = "Donate")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Preview() {
    ComposePlaygroundTheme {
        val exploreMainViewModel = hiltViewModel<ExploreMainViewModel>()
        val state by exploreMainViewModel.state.collectAsState()

        ExploreMainScreen(
            state = state,
            onEvent = { exploreMainViewModel.onEvent(it) },
        )
    }
}