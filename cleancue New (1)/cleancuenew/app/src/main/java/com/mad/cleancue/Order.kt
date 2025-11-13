package com.mad.cleancue // Use your app's package name

/**
 * Data class to represent a single order.
 * The fields match the views in list_item_order.xml
 */
data class Order(
    val customerName: String,
    val orderId: String,
    val lastUpdated: String,
    val status: String,
    val customerMobile: String? = null,
    val price: Double? = null,
    val weight : Double? = null,
    val items: Map<String, Int>,// Item name → Quantity
    val totalWeight: Double,     // in kg
    val totalPrice: Double
)