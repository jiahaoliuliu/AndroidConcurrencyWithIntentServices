package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log

class AddMoneyIntentService: IntentService("AddMoneyIntentService") {

    companion object {
        const val INTENT_KEY_QUANTITY = "intentKeyQuantity"

        @JvmStatic
        fun startServiceToAddMoney(context: Context, amount: Int,
                                   resultReceiverCallback: WalletResultReceiver.ResultReceiverCallBack<Int>) {
            val walletResultReceiver: WalletResultReceiver<Int> =
                    WalletResultReceiver(Handler(context.mainLooper))
            walletResultReceiver.receiver = resultReceiverCallback

            // Start the intent service
            val startAddMoneyIntentService =
                    Intent(context, AddMoneyIntentService::class.java)
            startAddMoneyIntentService.putExtra(
                    AddMoneyIntentService.INTENT_KEY_QUANTITY, amount)
            startAddMoneyIntentService.putExtra(
                    WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER, walletResultReceiver)
            context.startService(startAddMoneyIntentService)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i("AddMoneyIntentService", "New intent received to add money");

        // Get the quantity
        var quantityToAdd = 0
        intent.let {
            it?.extras.let {
                it?.let {
                    quantityToAdd = it.getInt(INTENT_KEY_QUANTITY)
                }
            }
        }

        // Get the receiver
        val resultReceiver = intent?.getParcelableExtra<ResultReceiver>(
                WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER)

        val walletManager = WalletManager.instance
        val finalQuantity = walletManager.quantity + quantityToAdd
        walletManager.quantity = finalQuantity
        val bundle = Bundle()
        bundle.putInt(WalletResultReceiver.BUNDLE_PARAM_RESULT, finalQuantity)

        resultReceiver?.send(WalletResultReceiver.RESULT_CODE_OK, bundle)
    }
}