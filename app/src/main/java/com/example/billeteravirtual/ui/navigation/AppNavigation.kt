package com.example.billeteravirtual.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.billeteravirtual.ui.screens.ReceiptScreen
import com.example.billeteravirtual.ui.screens.WithdrawScreen
import com.example.billeteravirtual.ui.viewmodel.WalletViewModel

// Definimos las rutas como constantes para evitar errores de tipeo
object Routes {
    const val WITHDRAW_SCREEN = "withdraw"
    const val RECEIPT_SCREEN = "receipt/{amount}"

    fun createReceiptRoute(amount: Double) = "receipt/$amount"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Obtenemos una instancia del ViewModel que vivirá durante toda la navegación
    val walletViewModel: WalletViewModel = viewModel()
    val uiState by walletViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Routes.WITHDRAW_SCREEN) {

        composable(Routes.WITHDRAW_SCREEN) {
            WithdrawScreen(

                navigateToReceipt = { amount ->
                    // Navega a la pantalla de recibo pasando el monto
                    navController.navigate(Routes.createReceiptRoute(amount))
                    // Limpiamos la transacción para no volver a navegar si la pantalla se recompone
                    //walletViewModel.eventHandled()
                },

            )
        }

        composable(
            route = Routes.RECEIPT_SCREEN,
            arguments = listOf(navArgument("amount") { type = NavType.FloatType })
        ) { backStackEntry ->
            // Recuperamos el monto de los argumentos de la ruta
            val amount = backStackEntry.arguments?.getFloat("amount")?.toDouble() ?: 0.0
            ReceiptScreen(
                amountWithdrawn = amount,
                onBackToHome = {
                    // Vuelve a la pantalla anterior
                    navController.popBackStack()
                }
            )
        }
    }
}