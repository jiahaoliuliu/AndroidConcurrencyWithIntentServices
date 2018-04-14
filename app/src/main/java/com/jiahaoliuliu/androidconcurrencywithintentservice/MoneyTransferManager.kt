package com.jiahaoliuliu.androidconcurrencywithintentservice

class MoneyTransferManager {

    companion object {
        @JvmField
        var quantity = 0
    }

    fun addMoney(quantityToAdd:Int):Int {
        quantity += quantityToAdd
        return quantity
    }
}