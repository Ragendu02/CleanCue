package com.mad.cleancue

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mad.cleancue.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDashboardData()
        setupNavigationListeners()
    }

    // ✅ Refresh dashboard every time user returns
    override fun onResume() {
        super.onResume()
        loadDashboardData()
    }

    /**
     * Loads live data from OrderStorage instead of static values
     */
    private fun loadDashboardData() {
        val orders = OrderStorage.getOrders(this)

        binding.tvTotalOrdersCount.text = orders.size.toString()
        binding.tvInProgressCount.text = orders.count { it.status == "In Progress" }.toString()
        binding.tvReadyCount.text = orders.count { it.status == "Ready for Pickup" }.toString()
        binding.tvDeliveredCount.text = orders.count { it.status == "Delivered" }.toString()
    }

    /**
     * Navigation buttons
     */
    private fun setupNavigationListeners() {

        binding.btnAddOrder.setOnClickListener {
            startActivity(Intent(this, AddOrderActivity::class.java))
        }

        binding.btnViewOrders.setOnClickListener {
            startActivity(Intent(this, AllOrderActivity::class.java))
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
