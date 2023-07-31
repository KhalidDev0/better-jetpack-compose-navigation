package com.example.composeplayground.presentation.common.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface BaseListener

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.getListener(navController: NavHostController, route: String): BaseListener {
    this.destination.parent?.route
    val previousScreenEntry = remember(this){ navController.getBackStackEntry(route = route) }
    return viewModel<T>(previousScreenEntry) as BaseListener
}