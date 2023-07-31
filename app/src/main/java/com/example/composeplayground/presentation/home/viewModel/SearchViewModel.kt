package com.example.composeplayground.presentation.home.viewModel

import android.util.Log
import androidx.navigation.NavHostController
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.base.BaseViewModel
import com.example.composeplayground.presentation.home.graph.HomeFlow
import com.example.composeplayground.presentation.home.utils.SearchEvent
import com.example.composeplayground.presentation.home.utils.SearchState
import com.example.composeplayground.presentation.payment.utils.PaymentListener
import com.example.composeplayground.presentation.payment.utils.PaymentResultEvent
import com.example.composeplayground.presentation.payment.utils.PaymentListenerRequirement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel<SearchState, SearchEvent>(), PaymentListener {
    override val privateState = MutableStateFlow(SearchState())

    override lateinit var navController: NavHostController

    override fun onEvent(event: SearchEvent) {
        privateState.apply {
            when (event) {
                is SearchEvent.Pay -> navController.navigate(Graph.PAYMENT)
                is SearchEvent.NavigateToExplore -> navController.navigate(HomeFlow.Explore.route)
            }
        }
    }

    override val paymentListenerRequirement = PaymentListenerRequirement(paymentAmount = 800)

    override fun onPaymentResultEvent(event: PaymentResultEvent) {
        when (event) {
            is PaymentResultEvent.PaymentSuccess -> Log.d(
                "TEST",
                "Payment success: ${event.paymentID}, Amount: ${event.paymentAmount} SR, Search Value: ${privateState.value.searchValue}"
            )

            is PaymentResultEvent.PaymentFailed -> Log.d("TEST", "Payment failed")
            is PaymentResultEvent.SendPaymentID -> Log.d("TEST", event.paymentID)
        }
    }
}
