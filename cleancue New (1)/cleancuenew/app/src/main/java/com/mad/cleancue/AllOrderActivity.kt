package com.mad.cleancue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.cleancue.databinding.ActivityOrderListBinding // Import your generated ViewBinding class

/**
 * Activity to display a list of all orders.
 * This activity corresponds to the layout file `activity_order_list.xml`.
 */
class AllOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    /**
     * Configures the RecyclerView with a LayoutManager and the custom OrderAdapter.
     */
    private fun setupRecyclerView() {
        // Set the LayoutManager
        binding.rvOrderList.layoutManager = LinearLayoutManager(this)

        // --- This is the new/updated part ---

        // 1. Create your list of Order data.
        //    (Later, you will fetch this from a database, API, or ViewModel)
        val dummyOrderList = listOf(
            Order(
                customerName = "ANJALI",
                orderId = "Order #1024",
                lastUpdated = "Updated 15 mins ago",
                status = "In Progress",
                customerMobile = "+91 98765 43210",
                items = mapOf("T-Shirt" to 5, "Jeans" to 2, "Jacket" to 1, "Formal Shirt" to 3),
                totalWeight = 7.5, // must provide
                totalPrice = 1200.0 // must provide
            ),
            Order(
                customerName = "Faheem Abdurahiman",
                orderId = "Order #1025",
                lastUpdated = "Updated 1 hour ago",
                status = "Shipped",
                customerMobile = "+91 91234 56789",
                items = mapOf("T-Shirt" to 3, "Jeans" to 2),
                totalWeight = 4.0,
                totalPrice = 800.0
            ),
            Order(
                customerName = "John Smith",
                orderId = "Order #1026",
                lastUpdated = "Updated 2 days ago",
                status = "Delivered",
                customerMobile = "+1 555-123-4567",
                items = mapOf("Shirt" to 4, "Pants" to 2),
                totalWeight = 5.5,
                totalPrice = 950.0
            ),
            Order(
                customerName = "Alex Johnson",
                orderId = "Order #1027",
                lastUpdated = "Updated 5 mins ago",
                status = "Pending",
                customerMobile = "+1 555-987-6543",
                items = mapOf("T-Shirt" to 2, "Shorts" to 3),
                totalWeight = 3.0,
                totalPrice = 500.0
            ),
            Order(
                customerName = "Maria Garcia",
                orderId = "Order #1028",
                lastUpdated = "Updated 1 day ago",
                status = "Cancelled",
                customerMobile = "+44 7700 900123",
                items = mapOf("Dress" to 1, "Skirt" to 2),
                totalWeight = 2.5,
                totalPrice = 600.0
            ),
            Order(
                customerName = "Kenji Tanaka",
                orderId = "Order #1029",
                lastUpdated = "Updated 3 hours ago",
                status = "In Progress",
                customerMobile = "+81 90-1234-5678",
                items = mapOf("Jacket" to 1, "Jeans" to 3, "Shirt" to 2),
                totalWeight = 6.0,
                totalPrice = 1100.0
            )
        )



        // 2. Create an instance of your new OrderAdapter
        val orderAdapter = OrderAdapter(dummyOrderList)

        // 3. Set the adapter on your RecyclerView
        binding.rvOrderList.adapter = orderAdapter
    }

    // --- The setupDummyAdapter() and SimpleViewHolder class are no longer needed,
    //     so you can delete them from this file. ---
}