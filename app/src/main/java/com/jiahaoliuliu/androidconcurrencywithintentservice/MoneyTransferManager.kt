package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferManager () {

    companion object {
        @JvmField
        val TAG = "MoneyTransferManager"
        var quantity = 0
    }

    init {
        Log.i(TAG, "Money transfer manager created")
    }

    fun addMoney(quantityToAdd:Int):Int {
        quantity = quantity + quantityToAdd
        return quantity
    }
}