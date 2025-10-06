package com.example.billeteravirtual.model

/**
 * Entidad de dominio simple para modelar una transacción
 */

data class Transaction(
    val amount: Double,
    val type: String,
    val timestamp: Long = System.currentTimeMillis()
)