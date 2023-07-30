package com.example.composeplayground.presentation.payment.viewModel

import androidx.navigation.NavHostController
import com.example.composeplayground.presentation.common.base.BaseViewModel
import com.example.composeplayground.presentation.payment.utils.PaymentListener
import com.example.composeplayground.presentation.payment.utils.PaymentListenerEvent
import com.example.composeplayground.presentation.payment.utils.SummaryEvent
import com.example.composeplayground.presentation.payment.utils.SummaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor() : BaseViewModel<SummaryState, SummaryEvent>() {
    override val privateState = MutableStateFlow(SummaryState())

    lateinit var listener: PaymentListener
    lateinit var listenerRoute: String
    override lateinit var navController: NavHostController

    override fun onEvent(event: SummaryEvent) {
        privateState.apply {
            when (event) {
                is SummaryEvent.PopUpWithSuccess -> {
                    listener.onPaymentListenerEvent(
                        PaymentListenerEvent.PaymentSuccess(
                            paymentAmount = listener.paymentListenerRequirement.paymentAmount,
                            paymentID = privateState.value.paymentID
                        )
                    )

                    navController.popBackStack(route = listenerRoute, inclusive = false)
                }
            }
        }
    }
}