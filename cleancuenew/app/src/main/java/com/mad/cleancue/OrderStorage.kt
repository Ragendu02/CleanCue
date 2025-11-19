package com.mad.cleancue

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrderStorage {

    private const val PREF_NAME = "orders_pref"
    private const val KEY_ORDERS = "orders_list"

    fun saveOrder(context: Context, order: Order) {
        val orders = getOrders(context).toMutableList()
        orders.add(order)
        saveOrders(context, orders)
    }

    fun deleteOrder(context: Context, orderId: String) {
        val orders = getOrders(context).toMutableList()
        val newList = orders.filter { it.orderId != orderId }
        saveOrders(context, newList)
    }

    fun getOrders(context: Context): List<Order> {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(KEY_ORDERS, null) ?: return emptyList()
        val type = object : TypeToken<List<Order>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun saveOrders(context: Context, orders: List<Order>) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val json = Gson().toJson(orders)
        editor.putString(KEY_ORDERS, json)
        editor.apply()
    }
}
