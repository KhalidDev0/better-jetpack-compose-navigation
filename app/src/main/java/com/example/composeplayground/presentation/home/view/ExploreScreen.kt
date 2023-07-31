package com.example.composeplayground.presentation.home.view

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
import com.example.composeplayground.presentation.home.utils.ExploreEvent
import com.example.composeplayground.presentation.home.utils.ExploreState
import com.example.composeplayground.presentation.home.viewModel.ExploreViewModel
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun ExploreScreen(
    state: ExploreState,
    onEvent: (ExploreEvent) -> Unit,
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
            ) { onEvent(ExploreEvent.ExpandName) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            CustomAnimatedName(name = state.firstName, expanded = state.expanded)

            CustomAnimatedName(name = state.secondName, expanded = state.expanded)
        }

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = {
            onEvent(ExploreEvent.ChangeBothNames(firstName = state.secondName, secondName = state.firstName))
        }) {
            Text(text = "Swap")
        }

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = { onEvent(ExploreEvent.Pay) }) {
            Text(text = "Donate")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewExplore() {
    ComposePlaygroundTheme {
        val exploreViewModel = hiltViewModel<ExploreViewModel>()
        val state by exploreViewModel.state.collectAsState()

        ExploreScreen(
            state = state,
            onEvent = { exploreViewModel.onEvent(it) },
        )
    }
}