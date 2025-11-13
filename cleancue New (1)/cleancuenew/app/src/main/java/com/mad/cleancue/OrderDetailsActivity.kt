package com.mad.cleancue

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val tvCustomerName = findViewById<TextView>(R.id.tv_customer_name)
        val tvCustomerMobile = findViewById<TextView>(R.id.tv_customer_mobile)
        val tvOrderId = findViewById<TextView>(R.id.tv_order_id)
        val tvOrderItems = findViewById<TextView>(R.id.tv_order_items)
        val tvDateReceived = findViewById<TextView>(R.id.tv_date_received)
        val tvDateDelivery = findViewById<TextView>(R.id.tv_date_delivery)
        val tvTotalWeight = findViewById<TextView>(R.id.tv_total_weight)
        val tvTotalPrice = findViewById<TextView>(R.id.tv_total_price)

        toolbar.setNavigationOnClickListener { finish() }

        // Get intent data
        val customerName = intent.getStringExtra("customerName")
        val orderId = intent.getStringExtra("orderId")
        val status = intent.getStringExtra("status")
        val lastUpdated = intent.getStringExtra("lastUpdated")
        val customerMobile = intent.getStringExtra("customerMobile")
        val orderItems = intent.getSerializableExtra("orderItems") as? HashMap<String, Int>
        val totalWeight = intent.getDoubleExtra("totalWeight", 0.0)
        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)

        // Display details
        tvCustomerName.text = customerName ?: "Unknown"
        tvCustomerMobile.text = customerMobile ?: "N/A"
        tvOrderId.text = orderId ?: "N/A"

        tvOrderItems.text = orderItems?.entries
            ?.joinToString(separator = "\n") { "• ${it.value} × ${it.key}" }
            ?: "No items"

        tvDateReceived.text = "Received: $lastUpdated"
        tvDateDelivery.text = "Status: $status"
        tvTotalWeight.text = "Total Weight: $totalWeight kg"
        tvTotalPrice.text = "Total Price: ₹$totalPrice"
    }
}
