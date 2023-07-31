package com.example.composeplayground.presentation.home.utils

sealed class SearchEvent {
    object Pay : SearchEvent()
    object NavigateToExplore : SearchEvent()
}
