package com.mad.cleancue

import android.app.DatePickerDialog
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

        // Set up the "Current Status" dropdown menu
        setupStatusDropdown()

        // Set up the date picker dialogs for the date fields
        setupDatePickers()

        // Set up the save/notify button listeners
        setupButtonListeners()

        // Generate a unique order ID
        generateOrderId()
    }

    /**
     * Populates the "Current Status" dropdown with options.
     */
    private fun setupStatusDropdown() {
        val orderStatuses = listOf("Pending", "In Progress", "Ready for Pickup", "Delivered")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, orderStatuses)
        binding.actvStatus.setAdapter(adapter)
    }

    /**
     * Adds click listeners to the Order Date and Delivery Date fields
     * to show a DatePickerDialog.
     */
    private fun setupDatePickers() {
        // Date picker listener
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        // Listener for the "Order Date" field
        binding.etOrderDate.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Listener for the "Delivery Date" field
        // Note: This uses the same logic. You might want to use a separate
        // listener or variable to update `etDeliveryDate` specifically.
        // For this example, I'll update `etDeliveryDate` with a separate listener.

        binding.etDeliveryDate.setOnClickListener {
            showDatePickerDialog(false)
        }

        binding.etOrderDate.setOnClickListener {
            showDatePickerDialog(true)
        }
    }

    /**
     * Helper function to show a DatePickerDialog and update the correct EditText.
     * @param isOrderDate True if updating Order Date, False for Delivery Date.
     */
    private fun showDatePickerDialog(isOrderDate: Boolean) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            // Format the date
            val format = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(format, Locale.US)
            val dateString = sdf.format(calendar.time)

            // Update the correct text field
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

    // This function is part of the original example but is now replaced by showDatePickerDialog
    private fun updateDateInView() {
        val format = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        // This line is ambiguous, so I replaced it.
        // binding.etOrderDate.setText(sdf.format(calendar.time))
    }

    /**
     * Sets up click listeners for the "Save" and "Send Notification" buttons.
     */
    private fun setupButtonListeners() {
        binding.btnSaveOrder.setOnClickListener {
            // In a real app, you would get all data and save to a database.
            val customerName = binding.etCustomerName.text.toString()
            if (customerName.isNotEmpty()) {
                Toast.makeText(this, "Order saved for $customerName", Toast.LENGTH_LONG).show()
                finish() // Go back to the dashboard
            } else {
                Toast.makeText(this, "Please enter a customer name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSendNotification.setOnClickListener {
            Toast.makeText(this, "Notification sent!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Generates a simple, unique Order ID placeholder.
     */
    private fun generateOrderId() {
        // Simple unique ID based on timestamp
        val orderId = "ORD-${System.currentTimeMillis() % 10000}"
        binding.etOrderId.setText(orderId)
    }
}