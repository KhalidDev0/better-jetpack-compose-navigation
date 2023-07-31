package com.example.composeplayground.presentation.home.viewModel

import android.util.Log
import androidx.navigation.NavHostController
import com.example.composeplayground.presentation.common.Graph
import com.example.composeplayground.presentation.common.base.BaseViewModel
import com.example.composeplayground.presentation.home.utils.ExploreEvent
import com.example.composeplayground.presentation.home.utils.ExploreState
import com.example.composeplayground.presentation.payment.utils.PaymentListener
import com.example.composeplayground.presentation.payment.utils.PaymentResultEvent
import com.example.composeplayground.presentation.payment.utils.PaymentListenerRequirement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : BaseViewModel<ExploreState, ExploreEvent>(), PaymentListener {
    override val privateState = MutableStateFlow(ExploreState())

    override lateinit var navController: NavHostController

    override fun onEvent(event: ExploreEvent) {
        privateState.apply {
            when (event) {
                is ExploreEvent.ExpandName -> value = value.copy(expanded = !value.expanded)
                is ExploreEvent.ChangeBothNames -> value = value.copy(firstName = event.firstName, secondName = event.secondName)
                is ExploreEvent.Pay -> navController.navigate(Graph.PAYMENT)
            }
        }
    }

    override val paymentListenerRequirement = PaymentListenerRequirement(paymentAmount = 400)

    override fun onPaymentResultEvent(event: PaymentResultEvent) {
        when (event) {
            is PaymentResultEvent.PaymentSuccess -> Log.d("TEST",
                    "Payment success: ${event.paymentID}," +
                            " Amount: ${event.paymentAmount} SR," +
                            " First Name: ${privateState.value.firstName}")
            is PaymentResultEvent.PaymentFailed -> Log.d("TEST", "Payment failed")
            is PaymentResultEvent.SendPaymentID -> Log.d("TEST", event.paymentID)
        }
    }
}
