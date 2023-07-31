package com.example.composeplayground.presentation.home.utils

sealed class ExploreEvent {
    object ExpandName : ExploreEvent()
    object Pay : ExploreEvent()
    data class ChangeBothNames(val firstName: String, val secondName: String) : ExploreEvent()
}
