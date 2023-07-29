package com.example.composeplayground.presentation.explore.viewModel

import android.util.Log
import androidx.navigation.NavHostController
import com.example.composeplayground.presentation.common.base.BaseViewModel
import com.example.composeplayground.presentation.explore.graph.ExploreFlow
import com.example.composeplayground.presentation.explore.utils.ExploreMainEvent
import com.example.composeplayground.presentation.explore.utils.ExploreMainState
import com.example.composeplayground.presentation.payment.utils.PaymentListener
import com.example.composeplayground.presentation.payment.utils.PaymentListenerEvent
import com.example.composeplayground.presentation.payment.utils.PaymentListenerRequirement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreMainViewModel @Inject constructor() : BaseViewModel<ExploreMainState, ExploreMainEvent>(), PaymentListener {
    override val privateState = MutableStateFlow(ExploreMainState())

    lateinit var navController: NavHostController

    override fun onEvent(event: ExploreMainEvent) {
        privateState.apply {
            when (event) {
                is ExploreMainEvent.ExpandName -> value = value.copy(expanded = !value.expanded)
                is ExploreMainEvent.ChangeBothNames -> value =
                    value.copy(firstName = event.firstName, secondName = event.secondName)

                is ExploreMainEvent.Pay -> navController.navigate(ExploreFlow.Payment.route)
            }
        }
    }

    override val paymentListenerRequirement = PaymentListenerRequirement(paymentAmount = 400)

    override fun onPaymentListenerEvent(event: PaymentListenerEvent) {
        when (event) {
            is PaymentListenerEvent.PaymentSuccess -> Log.d(
                "TEST",
                "Payment success: ${event.paymentID}, Amount: ${event.paymentAmount} SR, First Name:${privateState.value.firstName}"
            )

            is PaymentListenerEvent.PaymentFailed -> Log.d("TEST", "Payment failed")
            is PaymentListenerEvent.SendPaymentID -> Log.d("TEST", event.paymentID)
        }
    }
}
