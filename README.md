👛 Billetera Virtual en Jetpack Compose

¡Bienvenido/a al proyecto Billetera Virtual!
Esta es una aplicación Android moderna, construida 100% con Jetpack Compose, que simula la funcionalidad básica de una billetera digital:
ver tu saldo, realizar un retiro y obtener un comprobante de la transacción.

Este proyecto es un excelente ejemplo para aprender los fundamentos de la arquitectura recomendada por Google para aplicaciones Android modernas.

✨ Características Principales

📱 Pantalla Principal de Retiro: Muestra tu saldo actual y te permite ingresar un monto para retirar.

💰 Lógica de Validación: Evita retiros mayores al saldo disponible o montos inválidos (cero o negativos).

🧾 Pantalla de Comprobante: Tras un retiro exitoso, la app navega a una pantalla que confirma la transacción y muestra el monto retirado.

💅 UI Moderna y Reactiva: Interfaz construida con Material Design 3 y Jetpack Compose, que se actualiza automáticamente ante cambios de estado.

🛠️ Tecnologías y Arquitectura

Este proyecto sigue la arquitectura limpia (Clean Architecture) recomendada por Google, utilizando las herramientas más actuales para el desarrollo Android.

Categoría	Tecnología
💻 Lenguaje	Kotlin
🎨 Kit de UI	Jetpack Compose
📦 Componentes de UI	Material Design 3
🧠 Gestión de Estado	ViewModel + StateFlow
🗺️ Navegación	Navigation Compose
🏗️ Arquitectura	MVVM (Model - View - ViewModel)
🏗️ Estructura del Proyecto

La aplicación está organizada en capas bien definidas, lo que facilita su mantenimiento y escalabilidad:

1. ui/screens — La Interfaz (👀 Lo que ves)

Contiene las pantallas principales de la app, definidas como funciones @Composable.
Su función es mostrar datos y capturar eventos del usuario, sin incluir lógica de negocio.

WithdrawScreen.kt:
Pantalla principal. Muestra el saldo, el campo para ingresar el monto y el botón de retiro.
No ejecuta lógica directamente; solo envía los eventos al ViewModel.

ReceiptScreen.kt:
Pantalla de confirmación. Muestra el monto retirado y un mensaje de éxito.
No conoce la lógica detrás de la operación.

2. ui/navigation — El Director de Tráfico (🚦)

AppNavigation.kt:
Define las rutas de la aplicación (por ejemplo, "withdraw" y "receipt/{amount}").
Utiliza un NavController para gestionar la navegación entre pantallas.
Es el único módulo que conoce el “mapa” de la app.

3. ui/viewmodel — El Cerebro (🧠)

WalletViewModel.kt:
Contiene toda la lógica del negocio y el estado de la aplicación.

Estado (WalletUiState): Guarda el saldo actual, el último retiro y posibles mensajes de error.

Lógica: Implementa la función withdraw(), que valida el monto, actualiza el saldo y crea una nueva transacción.

Comunicación con la UI: No interactúa directamente con las pantallas; simplemente actualiza el estado, y Compose se encarga de redibujar la interfaz automáticamente.

4. model — Los Datos (📝)

Transaction.kt:
Data class que representa una transacción (en este caso, un retiro).

🌊 Flujo de Funcionamiento (Paso a Paso)

¿Qué sucede cuando retiras 50 €?

👆 El usuario pulsa el botón “Retirar” en WithdrawScreen.

🗣️ La UI notifica al WalletViewModel llamando a viewModel.withdraw(50.0).

🧠 El ViewModel ejecuta la lógica:

Verifica que el monto sea válido.

Comprueba que haya saldo suficiente.

Si todo está correcto, calcula el nuevo saldo, crea una Transaction y actualiza el estado.

🎨 La UI reacciona automáticamente:

WithdrawScreen observa los cambios en uiState.

Detecta que lastTransaction ya no es null.

Un LaunchedEffect se dispara y ejecuta navigateToReceipt(50.0).

🗺️ La navegación se realiza a través de AppNavigation, que dirige a la ruta "receipt/50.0".

🎉 Éxito: Se muestra ReceiptScreen con el mensaje de retiro exitoso y el monto correspondiente.

💬 Conclusión

¡Y así funciona una app moderna con Jetpack Compose, ViewModel y StateFlow!
Gracias por explorar este proyecto — una base ideal para seguir aprendiendo desarrollo Android moderno. 🚀
