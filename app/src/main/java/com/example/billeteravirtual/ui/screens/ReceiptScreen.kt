// En: app/src/main/java/com/example/billeteravirtual/ui/screens/ReceiptScreen.kt
package com.example.billeteravirtual.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Asegúrate de que esta función exista, sea @Composable y pública
@Composable
fun ReceiptScreen(
    amountWithdrawn: Double,
    onBackToHome: () -> Unit
) {
    // 2. Implementa la UI usando un Column para organizar los elementos verticalmente
    Column(
        modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {
        Text(
            text = "¡Retiro exitoso!",
            style = MaterialTheme.typography.headlineMedium // Usa un estilo de texto predefinido
        )
        Spacer(modifier = Modifier.height(16.dp)) // Añade un espacio
        Text(
            text = "Monto retirado: $${String.format("%.2f", amountWithdrawn)}", // Formatea el monto a 2 decimales
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp)) // Un espacio más grande
        Button(onClick = onBackToHome) {
            Text("Volver al inicio")
        }
    }
    // Aquí iría tu UI para mostrar el recibo.
    // Ejemplo básico:

    // Column {
    //     Text(text = "¡Retiro exitoso!")
    //     Text(text = "Monto retirado: $amountWithdrawn")
    //     Button(onClick = onBackToHome) {
    //         Text("Volver al inicio")
    //     }
    // }
}

// 3. (Opcional pero recomendado) Añade una Previsualización para ver cómo luce tu pantalla
@Preview(showBackground = true)
@Composable
fun ReceiptScreenPreview() {
    // Llama a tu pantalla con datos de ejemplo
    ReceiptScreen(amountWithdrawn = 150.50, onBackToHome = {})

}

