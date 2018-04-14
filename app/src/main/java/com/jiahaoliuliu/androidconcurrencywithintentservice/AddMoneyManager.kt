package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.content.Context

class AddMoneyManager(context: Context) {

    private val context: Context = context

    fun addMoney(quantityToAdd: Int,
                 resultReceiverCallback: WalletResultReceiver.ResultReceiverCallBack<Int>) {
        AddMoneyIntentService.startServiceToAddMoney(context, quantityToAdd, resultReceiverCallback)
    }
}