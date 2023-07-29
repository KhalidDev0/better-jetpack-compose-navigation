package com.example.composeplayground.presentation.home.graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.ROUTE_HOME_EXPLORE
import com.example.composeplayground.presentation.common.ROUTE_HOME_SEARCH
import com.example.composeplayground.presentation.common.ROUTE_HOME_SETTINGS
import com.example.composeplayground.presentation.explore.graph.ExploreNavGraph

@Composable
fun HomeNavGraph(){
    val navController = rememberNavController()

    NavHost(route = Graph.HOME, startDestination = HomeFlow.Explore.route, navController = navController){

        composable(route = HomeFlow.Explore.route){
            ExploreNavGraph()
        }

        composable(route = HomeFlow.Search.route){
            Text(text = "Route: ${HomeFlow.Search.route}", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        }

        composable(route = HomeFlow.Settings.route){
            Text(text = "Route: ${HomeFlow.Settings.route}", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        }
    }
}

sealed class HomeFlow(val route: String){
    object Explore: HomeFlow(ROUTE_HOME_EXPLORE)
    object Search: HomeFlow(ROUTE_HOME_SEARCH)
    object Settings: HomeFlow(ROUTE_HOME_SETTINGS)
}