package com.example.composeplayground.presentation.common

// Graphs Route
object Graph {
    const val MAIN = "ROOT_GRAPH"
    const val HOME = "HOME_GRAPH"
    const val EXPLORE = "EXPLORE_GRAPH"
    const val PAYMENT = "PAYMENT_GRAPH"
}

// Home Routes
const val ROUTE_HOME_EXPLORE = Graph.EXPLORE
const val ROUTE_HOME_SEARCH = "HOME_SEARCH"
const val ROUTE_HOME_SETTINGS = "HOME_SETTINGS"

// Explore Routes
const val ROUTE_EXPLORE_MAIN = "EXPLORE_MAIN"
const val ROUTE_EXPLORE_PAYMENT = Graph.PAYMENT

// Payment Routes
const val ROUTE_PAYMENT_SUMMARY = "PAYMENT_SUMMARY"
const val ROUTE_PAYMENT_OPTIONS = "PAYMENT_SUMMARY_OPTIONS"