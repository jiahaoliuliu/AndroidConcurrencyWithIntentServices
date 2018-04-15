package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import java.lang.Thread.sleep

class AddSalaryIntentService: IntentService("AddMoneyIntentService") {

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
                    Intent(context, AddSalaryIntentService::class.java)
            startAddMoneyIntentService.putExtra(
                    AddSalaryIntentService.INTENT_KEY_QUANTITY, amount)
            startAddMoneyIntentService.putExtra(
                    WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER, walletResultReceiver)
            context.startService(startAddMoneyIntentService)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i("AddMoneyIntentService", "New intent received to add money");
        Log.i("Thread", "Intent service. The current thread is " + Thread.currentThread().id)

        // Get the quantity
        val quantityToAdd = intent?.extras?.getInt(INTENT_KEY_QUANTITY) ?: 0

        // Get the receiver
        val resultReceiver = intent?.getParcelableExtra<ResultReceiver>(
                WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER)

        // Adding the quantity. This is the core of this app
        val walletManager = WalletManager.instance
        val currentQuantity = walletManager.quantity
        // Wait for 5s to reproduce the error
        sleep(5000)
        val finalQuantity = currentQuantity + quantityToAdd
        walletManager.quantity = finalQuantity

        // Update the result to the caller
        val bundle = Bundle()
        bundle.putInt(WalletResultReceiver.BUNDLE_PARAM_RESULT, finalQuantity)
        resultReceiver?.send(WalletResultReceiver.RESULT_CODE_OK, bundle)
    }
}