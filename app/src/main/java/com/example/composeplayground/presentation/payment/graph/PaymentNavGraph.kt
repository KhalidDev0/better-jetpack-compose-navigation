package com.example.composeplayground.presentation.payment.graph

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.ROUTE_PAYMENT_OPTIONS
import com.example.composeplayground.presentation.common.ROUTE_PAYMENT_SUMMARY
import com.example.composeplayground.presentation.payment.utils.PaymentListener
import com.example.composeplayground.presentation.payment.view.SummaryScreen
import com.example.composeplayground.presentation.payment.viewModel.SummaryViewModel

fun NavGraphBuilder.paymentNavGraph(
    navController: NavHostController,
    listenerRoute: String,
    listenerProvider: @Composable (NavBackStackEntry) -> PaymentListener,
) {
    navigation(route = Graph.PAYMENT, startDestination = PaymentFlow.Options.route) {

        composable(route = PaymentFlow.Options.route) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(PaymentFlow.Summary.route) }
            ) {
                Text(text = "Payment Options (Click Anywhere)", style = TextStyle(fontSize = 20.sp, color = Color.Black))
            }
        }

        composable(route = PaymentFlow.Summary.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(listenerRoute) }
            val listener: PaymentListener = listenerProvider(parentEntry)

            val summaryViewModel = hiltViewModel<SummaryViewModel>()
            summaryViewModel.listener = listener
            summaryViewModel.listenerRoute = listenerRoute
            summaryViewModel.navController = navController

            val state by summaryViewModel.state.collectAsState()

            SummaryScreen(
                state = state,
                onEvent = { summaryViewModel.onEvent(it) },
                listenerData = summaryViewModel.listener.paymentListenerRequirement,
            )
        }

    }
}

sealed class PaymentFlow(val route: String) {
    object Summary : PaymentFlow(ROUTE_PAYMENT_SUMMARY)
    object Options : PaymentFlow(ROUTE_PAYMENT_OPTIONS)
}