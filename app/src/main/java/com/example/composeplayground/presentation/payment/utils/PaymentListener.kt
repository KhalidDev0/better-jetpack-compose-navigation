package com.example.composeplayground.presentation.payment.utils

interface PaymentListener {
    val paymentListenerRequirement: PaymentListenerRequirement
    fun onPaymentListenerEvent(event: PaymentListenerEvent)
}

data class PaymentListenerRequirement(
    val paymentAmount: Int,
)

sealed class PaymentListenerEvent{
    data class PaymentSuccess(val paymentID: String, val paymentAmount: Int): PaymentListenerEvent()
    object PaymentFailed: PaymentListenerEvent()
    data class SendPaymentID(val paymentID: String): PaymentListenerEvent()
}
