package com.mad.cleancue

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mad.cleancue.databinding.ActivityAddOrderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddOrderBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStatusDropdown()
        setupDatePickers()
        setupButtonListeners()
        generateOrderId()
    }

    private fun setupStatusDropdown() {
        val orderStatuses = listOf("Pending", "In Progress", "Ready for Pickup", "Delivered")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, orderStatuses)
        binding.actvStatus.setAdapter(adapter)
    }

    private fun setupDatePickers() {
        binding.etOrderDate.setOnClickListener { showDatePickerDialog(true) }
        binding.etDeliveryDate.setOnClickListener { showDatePickerDialog(false) }
    }

    private fun showDatePickerDialog(isOrderDate: Boolean) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(format, Locale.US)
            val dateString = sdf.format(calendar.time)

            if (isOrderDate) {
                binding.etOrderDate.setText(dateString)
            } else {
                binding.etDeliveryDate.setText(dateString)
            }
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun setupButtonListeners() {

        // SAVE ORDER BUTTON
        binding.btnSaveOrder.setOnClickListener {

            val customerName = binding.etCustomerName.text.toString()
            val orderId = binding.etOrderId.text.toString()
            val status = binding.actvStatus.text.toString()
            val orderDate = binding.etOrderDate.text.toString()

            if (customerName.isEmpty() || status.isEmpty()) {
                Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val order = Order(
                customerName = customerName,
                orderId = orderId,
                lastUpdated = orderDate,
                status = status,
                customerMobile = "",
                items = emptyMap(),
                totalWeight = 0.0,
                totalPrice = 0.0
            )

            OrderStorage.saveOrder(this, order)

            Toast.makeText(this, "Order Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }


        // SEND NOTIFICATION BUTTON
        binding.btnSendNotification.setOnClickListener {
            Toast.makeText(this, "Notification sent!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateOrderId() {
        val orderId = "ORD-${System.currentTimeMillis() % 10000}"
        binding.etOrderId.setText(orderId)
    }
}
