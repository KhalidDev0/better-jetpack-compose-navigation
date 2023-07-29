package com.example.composeplayground.presentation.main.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.home.graph.HomeNavGraph

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(route = Graph.MAIN, startDestination = Graph.HOME, navController = navController) {
        composable(route = Graph.HOME) {
            HomeNavGraph()
        }
    }
}