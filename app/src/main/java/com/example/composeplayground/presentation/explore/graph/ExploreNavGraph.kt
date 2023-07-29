package com.example.composeplayground.presentation.explore.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.ROUTE_EXPLORE_MAIN
import com.example.composeplayground.presentation.common.ROUTE_EXPLORE_PAYMENT
import com.example.composeplayground.presentation.explore.view.ExploreMainScreen
import com.example.composeplayground.presentation.explore.viewModel.ExploreMainViewModel
import com.example.composeplayground.presentation.payment.graph.paymentNavGraph

@Composable
fun ExploreNavGraph() {
    val navController = rememberNavController()

    NavHost(route = Graph.EXPLORE, startDestination = ExploreFlow.Main.route, navController = navController) {

        composable(route = ExploreFlow.Main.route) {
            val exploreMainViewModel = hiltViewModel<ExploreMainViewModel>()
            exploreMainViewModel.navController = navController

            val state by exploreMainViewModel.state.collectAsState()

            ExploreMainScreen(
                state = state,
                onEvent = { exploreMainViewModel.onEvent(it) },
            )

        }

        paymentNavGraph(
            navController = navController,
            listenerRoute = ExploreFlow.Main.route,
            listenerProvider = { backStackEntry ->
                hiltViewModel<ExploreMainViewModel>(backStackEntry)
            })
    }
}

sealed class ExploreFlow(val route: String) {
    object Main : ExploreFlow(ROUTE_EXPLORE_MAIN)
    object Payment : ExploreFlow(ROUTE_EXPLORE_PAYMENT)
}