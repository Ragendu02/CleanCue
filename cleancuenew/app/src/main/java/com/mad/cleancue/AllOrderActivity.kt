package com.mad.cleancue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.cleancue.databinding.ActivityOrderListBinding

class AllOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderListBinding
    private lateinit var orderAdapter: OrderAdapter

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
     * Loads orders from OrderStorage and sets up RecyclerView adapter.
     */
    private fun setupRecyclerView() {
        binding.rvOrderList.layoutManager = LinearLayoutManager(this)

        val orders = OrderStorage.getOrders(this).toMutableList()   // ← load saved orders
        orderAdapter = OrderAdapter(orders)

        binding.rvOrderList.adapter = orderAdapter
    }

    /**
     * Refresh list when returning back from delete or new order.
     */
    override fun onResume() {
        super.onResume()

        val updatedOrders = OrderStorage.getOrders(this).toMutableList()

        orderAdapter.apply {
            this.orders.clear()
            this.orders.addAll(updatedOrders)
            notifyDataSetChanged()
        }
    }
}
