package com.mad.cleancue

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mad.cleancue.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    // Using ViewBinding to interact with the UI
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate and set the layout
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val contentView = setContentView(binding.root)

        // Load placeholder data into the dashboard cards
        loadDashboardData()

        // Set up click listeners for the bottom navigation buttons
        setupNavigationListeners()
    }

    /**
     * Populates the dashboard cards with static data.
     * In a real app, you would fetch this from a database or API.
     */
    private fun loadDashboardData() {
        binding.tvTotalOrdersCount.text = "142"
        binding.tvInProgressCount.text = "12"
        binding.tvReadyCount.text = "9"
        binding.tvDeliveredCount.text = "121"
        binding.tvLiveStatusCount.text = "3 machines active"
    }

    /**
     * Sets up onClickListeners for the custom bottom navigation bar.
     */
    private fun setupNavigationListeners() {
        // "Add New" button starts the AddOrderActivity
        binding.btnAddOrder.setOnClickListener {
            val intent = Intent(this, AddOrderActivity::class.java)
            startActivity(intent)

        }

        // Other buttons just show a Toast message as a placeholder
        binding.btnViewOrders.setOnClickListener {
            val intent = Intent(this, AllOrderActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "View All Orders clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnNotifications.setOnClickListener {
            Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
        }
    }
}