package com.mad.cleancue

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mad.cleancue.databinding.ListItemOrderBinding

class OrderAdapter(internal val orders: MutableList<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(private val binding: ListItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.tvCustomerName.text = order.customerName
            binding.tvOrderId.text = order.orderId
            binding.tvLastUpdated.text = order.lastUpdated
            binding.tvOrderStatus.text = order.status

            val context = binding.root.context

            // Set status color dynamically
            val statusColor = when (order.status.lowercase()) {
                "in progress" -> Color.parseColor("#ED8936") // Orange
                "shipped" -> Color.parseColor("#4299E1")     // Blue
                "delivered" -> Color.parseColor("#48BB78")   // Green
                "cancelled" -> Color.parseColor("#F56565")   // Red
                "pending" -> Color.parseColor("#ECC94B")     // Yellow
                else -> Color.parseColor("#A0AEC0")          // Gray
            }
            binding.tvOrderStatus.backgroundTintList = ColorStateList.valueOf(statusColor)

            // Button actions
            binding.btnFinished.setOnClickListener {
                Toast.makeText(context, "Marked '${order.orderId}' as Finished", Toast.LENGTH_SHORT).show()
            }

            binding.btnPending.setOnClickListener {
                Toast.makeText(context, "Marked '${order.orderId}' as Pending", Toast.LENGTH_SHORT).show()
            }

            binding.btnRemove.setOnClickListener {
                OrderStorage.deleteOrder(context, order.orderId)

                // Remove from list and update UI
                val pos = adapterPosition
                orders.removeAt(pos)
                notifyItemRemoved(pos)

                Toast.makeText(context, "Order Removed!", Toast.LENGTH_SHORT).show()
            }


            // Navigate to OrderDetailsActivity with all details
            binding.root.setOnClickListener {
                val intent = Intent(context, OrderDetailsActivity::class.java).apply {
                    putExtra("customerName", order.customerName)
                    putExtra("orderId", order.orderId)
                    putExtra("status", order.status)
                    putExtra("lastUpdated", order.lastUpdated)
                    putExtra("customerMobile", order.customerMobile)
                    putExtra("orderItems", HashMap(order.items)) // convert Map to Serializable
                    putExtra("totalWeight", order.totalWeight)
                    putExtra("totalPrice", order.totalPrice)
                }
                context.startActivity(intent)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ListItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size
}