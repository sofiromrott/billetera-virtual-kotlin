package com.example.billeteravirtual.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billeteravirtual.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Clase para representar el estado de la UI de la billetera
data class WalletUiState(
    val currentBalance: Double = 1000.0, // Saldo inicial de ejemplo
    val lastTransaction: Transaction? = null,
    val errorMessage: String? = null
)

class WalletViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WalletUiState())
    val uiState = _uiState.asStateFlow() // Flujo de solo lectura para la UI


    /**
     * Intenta procesar un retiro.
     * @param amountToWithdraw El monto que el usuario quiere retirar.
     */
    fun withdraw(amountToWithdraw: Double) {
        val currentBalance = _uiState.value.currentBalance

        if (amountToWithdraw <= 0) {
            _uiState.update { it.copy(errorMessage = "El monto debe ser mayor a cero") }
            return
        }

        if (amountToWithdraw > currentBalance) {
            _uiState.update { it.copy(errorMessage = "Saldo insuficiente") }
            return
        }

        val newBalance = currentBalance - amountToWithdraw
        val transaction = Transaction(amount = amountToWithdraw, type = "Retiro")

        _uiState.update {
            it.copy(
                currentBalance = newBalance,
                lastTransaction = transaction,
                errorMessage = null
            )
        }
    }

    /**
     * Se llama cuando la UI ha terminado de manejar un evento
     * (por ejemplo, después de navegar o mostrar un mensaje).
     */
    fun eventHandled() {
        _uiState.update { it.copy(lastTransaction = null, errorMessage = null) }
    }
}
