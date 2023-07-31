package com.example.composeplayground.presentation.home.graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.ROUTE_HOME_EXPLORE
import com.example.composeplayground.presentation.common.ROUTE_HOME_SEARCH
import com.example.composeplayground.presentation.common.ROUTE_HOME_SETTINGS
import com.example.composeplayground.presentation.common.base.getListener
import com.example.composeplayground.presentation.home.view.ExploreScreen
import com.example.composeplayground.presentation.home.view.SearchScreen
import com.example.composeplayground.presentation.home.viewModel.ExploreViewModel
import com.example.composeplayground.presentation.home.viewModel.SearchViewModel
import com.example.composeplayground.presentation.payment.graph.paymentNavGraph

@Composable
fun HomeNavGraph(){
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf(HomeFlow.Search.route) }

    NavHost(route = Graph.HOME, startDestination = HomeFlow.Search.route, navController = navController){

        composable(route = HomeFlow.Explore.route){
            currentRoute = HomeFlow.Explore.route
            val exploreViewModel = hiltViewModel<ExploreViewModel>()
            exploreViewModel.navController = navController

            val state by exploreViewModel.state.collectAsState()

            ExploreScreen(
                state = state,
                onEvent = { exploreViewModel.onEvent(it) },
            )
        }

        composable(route = HomeFlow.Search.route){
            currentRoute = HomeFlow.Search.route
            val searchViewModel = hiltViewModel<SearchViewModel>()
            searchViewModel.navController = navController

            val state by searchViewModel.state.collectAsState()

            SearchScreen(
                state = state,
                onEvent = { searchViewModel.onEvent(it) },
            )
        }

        composable(route = HomeFlow.Settings.route){
            Text(text = "Route: ${HomeFlow.Settings.route}", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        }

        paymentNavGraph(
            navController = navController
        ) {
            when (currentRoute) {
                HomeFlow.Explore.route -> it.getListener<ExploreViewModel>(navController = navController, currentRoute)
                HomeFlow.Search.route -> it.getListener<SearchViewModel>(navController = navController, currentRoute)
                else -> throw IllegalStateException("No Listener found")
            }
        }
    }
}

sealed class HomeFlow(val route: String){
    object Explore: HomeFlow(ROUTE_HOME_EXPLORE)
    object Search: HomeFlow(ROUTE_HOME_SEARCH)
    object Settings: HomeFlow(ROUTE_HOME_SETTINGS)
}