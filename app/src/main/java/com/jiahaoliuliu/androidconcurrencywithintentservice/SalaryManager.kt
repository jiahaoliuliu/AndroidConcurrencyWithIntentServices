package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.content.Context

class SalaryManager(context: Context) {

    private val context: Context = context

    fun addSalary(quantityToAdd: Int,
                 resultReceiverCallback: WalletResultReceiver.ResultReceiverCallBack<Int>) {
        AddSalaryIntentService.startServiceToAddMoney(context, quantityToAdd, resultReceiverCallback)
    }

    fun addTips(quantityToAdd: Int,
                resultReceiverCallback: WalletResultReceiver.ResultReceiverCallBack<Int>) {
        AddTipsService.startServiceToAddTips(context, quantityToAdd, resultReceiverCallback)
    }
}