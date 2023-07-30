package com.example.composeplayground.presentation.common.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State, Event>: ViewModel(){
    protected abstract val privateState: MutableStateFlow<State>
    val state get() = privateState.asStateFlow()
    abstract var navController: NavHostController
    abstract fun onEvent(event: Event)
}