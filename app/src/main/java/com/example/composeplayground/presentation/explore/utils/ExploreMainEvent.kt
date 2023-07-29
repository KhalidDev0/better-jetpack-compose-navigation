package com.example.composeplayground.presentation.explore.utils

sealed class ExploreMainEvent {
    object ExpandName : ExploreMainEvent()
    object Pay : ExploreMainEvent()
    data class ChangeBothNames(val firstName: String, val secondName: String) : ExploreMainEvent()
}
