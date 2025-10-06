// En: app/src/main/java/com/example/billeteravirtual/ui/screens/WithdrawScreen.kt
package com.example.billeteravirtual.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billeteravirtual.ui.viewmodel.WalletViewModel
import kotlinx.coroutines.launch

// ¡CLAVE 1: LA FIRMA DE LA FUNCIÓN CAMBIA!
@Composable
fun WithdrawScreen(
    // La pantalla solo necesita saber CÓMO navegar. No necesita el ViewModel desde afuera.
    navigateToReceipt: (Double) -> Unit,
    walletViewModel: WalletViewModel = viewModel() // Pide el ViewModel directamente aquí.
) {
    // ¡CLAVE 2: OBTENEMOS EL ESTADO Y LOS EVENTOS DENTRO DE LA PANTALLA!
    val uiState by walletViewModel.uiState.collectAsState()
    val onWithdraw = { amount: Double -> walletViewModel.withdraw(amount) }
    val onErrorShown = { walletViewModel.eventHandled() }

    // El resto del código es tu nueva y bonita interfaz, que ahora funcionará.
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Este `LaunchedEffect` se ejecutará cuando `lastTransaction` cambie.
    LaunchedEffect(key1 = uiState.lastTransaction) {
        // Guardamos el valor en una variable local `transaction`
        val transaction = uiState.lastTransaction
        if (transaction != null) {
            navigateToReceipt(transaction.amount)
            onErrorShown() // Limpiamos el estado para no volver a navegar.
        }
    }

    // Este `LaunchedEffect` mostrará los errores.
    LaunchedEffect(key1 = uiState.errorMessage) {
        // Guardamos el valor en una variable local `error`
        val error = uiState.errorMessage
        if (error != null) {
            scope.launch {
                snackbarHostState.showSnackbar(message = error)
            }
            onErrorShown() // Limpiamos el error después de mostrarlo.
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Realizar un Retiro",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tu saldo actual es: $${String.format("%.2f", uiState.currentBalance)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(40.dp))
            var amountText by remember { mutableStateOf("") }
            OutlinedTextField(
                value = amountText,
                onValueChange = { amountText = it },
                label = { Text("Monto a retirar") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val amountToWithdraw = amountText.toDoubleOrNull() ?: 0.0
                    walletViewModel.withdraw(amountToWithdraw)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Retirar",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


// --- PREVISUALIZACIÓN ACTUALIZADA ---
@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun WithdrawScreenPreview() {
    // La previsualización ahora es más simple, solo necesita una función de navegación vacía.
    WithdrawScreen(navigateToReceipt = {})
}


