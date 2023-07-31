package com.example.composeplayground.presentation.payment.utils

import com.example.composeplayground.presentation.common.base.BaseListener

interface PaymentListener: BaseListener {
    val paymentListenerRequirement: PaymentListenerRequirement
    fun onPaymentResultEvent(event: PaymentResultEvent)
}

data class PaymentListenerRequirement(
    val paymentAmount: Int,
)

sealed class PaymentResultEvent{
    data class PaymentSuccess(val paymentID: String, val paymentAmount: Int): PaymentResultEvent()
    object PaymentFailed: PaymentResultEvent()
    data class SendPaymentID(val paymentID: String): PaymentResultEvent()
}
