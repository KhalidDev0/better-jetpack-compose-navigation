package com.example.composeplayground.presentation.payment.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeplayground.presentation.payment.utils.SummaryEvent
import com.example.composeplayground.presentation.payment.utils.PaymentListenerRequirement
import com.example.composeplayground.presentation.payment.utils.SummaryState
import com.example.composeplayground.presentation.payment.viewModel.SummaryViewModel
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun SummaryScreen(
    state: SummaryState,
    onEvent: (SummaryEvent) -> Unit,
    listenerData: PaymentListenerRequirement,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "Payment ID: ${state.paymentID}", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        Text(text = "Payment Amount: ${listenerData.paymentAmount} SR", style = TextStyle(fontSize = 20.sp, color = Color.Black))

        Button(modifier = Modifier.padding(vertical = 10.dp),onClick = {
            onEvent(SummaryEvent.PopUpWithSuccess)
        }) {
            Text(text = "Pay")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun Preview() {
    ComposePlaygroundTheme {
        val viewModel = hiltViewModel<SummaryViewModel>()
        val state by viewModel.state.collectAsState()

        SummaryScreen(
            state = state,
            onEvent = { viewModel.onEvent(it) },
            listenerData = PaymentListenerRequirement(paymentAmount = 500),
        )
    }
}