# Jetpack Compose Navigation Data Handling

This public GitHub repository provides an example implementation of how to handle data between screens in a Jetpack Compose Navigation flow. It includes several classes and interfaces to demonstrate how data can be passed between screens and how listeners can be used to trigger events.

## ExploreMainViewModel

This class is a Hilt ViewModel that extends `BaseViewModel` and implements the `PaymentListener` interface. It has several functions and properties that handle events and manage the state of the application. 

- `privateState`: A `MutableStateFlow` that holds the private state of the ViewModel.
- `navController`: A `NavHostController` that is used to navigate to different screens in the navigation flow.
- `onEvent`: A function that takes an `ExploreMainEvent` and updates the ViewModel's private state accordingly.
- `paymentListenerRequirement`: A `PaymentListenerRequirement` that specifies the payment amount required for payment.
- `onPaymentListenerEvent`: A function that takes a `PaymentListenerEvent` and handles the corresponding event.

## PaymentListener

This interface specifies the requirements for a payment listener, which is used to trigger payment-related events.

- `paymentListenerRequirement`: A `PaymentListenerRequirement` that specifies the payment amount required for payment.
- `onPaymentListenerEvent`: A function that takes a `PaymentListenerEvent` and handles the corresponding event.

## PaymentListenerRequirement

This data class specifies the payment amount required for payment.

- `paymentAmount`: An `Int` that specifies the payment amount required for payment.

## PaymentListenerEvent

This sealed class specifies the types of events that can be triggered by a payment listener.

- `PaymentSuccess`: A data class that holds information about a successful payment, including the payment ID and amount.
- `PaymentFailed`: An object that represents a failed payment.

## paymentNavGraph

This function is used to create a navigation graph for a payment flow. It takes a `NavHostController`, a listener route, and a listener provider as input. The listener provider is used to provide a payment listener to the payment flow.

A payment flow typically needs some data to operate, such as the payment amount and payment ID. Once the payment process is finished, it notifies its listener of the result, which could be a successful payment with a payment ID or a failed payment.

## Other NavGraphs

Other navigation graphs, such as `bookingNavGraph` or `ratingNavGraph`, work similarly to the `paymentNavGraph`. They may require data to operate and notify their listeners when they finish their respective processes.

It's important to note that the specific requirements for each navigation graph will vary depending on the specific use case. However, the general principles of using listeners to pass data between screens and trigger events remain the same.
