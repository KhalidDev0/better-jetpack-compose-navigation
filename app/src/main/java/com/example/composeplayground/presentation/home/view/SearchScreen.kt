package com.example.composeplayground.presentation.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeplayground.presentation.home.utils.SearchEvent
import com.example.composeplayground.presentation.home.utils.SearchState
import com.example.composeplayground.presentation.home.viewModel.SearchViewModel
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onEvent(SearchEvent.Pay) }
    ) {
        Text(text = "Search: ${state.searchValue}", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        Text(text = "Search (Click Anywhere to Pay)", style = TextStyle(fontSize = 20.sp, color = Color.Black))

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = {
            onEvent(SearchEvent.NavigateToExplore)
        }) {
            Text(text = "Explore")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewSearch() {
    ComposePlaygroundTheme {
        val exploreViewModel = hiltViewModel<SearchViewModel>()
        val state by exploreViewModel.state.collectAsState()

        SearchScreen(
            state = state,
            onEvent = { exploreViewModel.onEvent(it) },
        )
    }
}