package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log

class AddTipsService : Service() {

    companion object {
        const val INTENT_KEY_QUANTITY = "intentKeyQuantity"
        val binder = LocalBinder()

        @JvmStatic
        fun startServiceToAddTips(context: Context, amount: Int,
                                   resultReceiverCallback: WalletResultReceiver.ResultReceiverCallBack<Int>) {
            val walletResultReceiver: WalletResultReceiver<Int> =
                    WalletResultReceiver(Handler(context.mainLooper))
            walletResultReceiver.receiver = resultReceiverCallback

            // Start the intent service
            val startAddTipsServiceIntent =
                    Intent(context, AddTipsService::class.java)
            startAddTipsServiceIntent.putExtra(
                    AddTipsService.INTENT_KEY_QUANTITY, amount)
            startAddTipsServiceIntent.putExtra(
                    WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER, walletResultReceiver)
            context.startService(startAddTipsServiceIntent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("AddTipsService", "Process started again")
        increaseSalary(intent)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private fun increaseSalary(intent : Intent?) {
        Log.i("Thread", "service. The current thread is " + Thread.currentThread().id)
        // Get the receiver
        val resultReceiver = intent?.getParcelableExtra<ResultReceiver>(
                WalletResultReceiver.INTENT_PARAM_RESULT_RECEIVER)

        // Adding tips to the wallet. This is the core of this class
        val tips = intent?.extras?.getInt(INTENT_KEY_QUANTITY) ?: 0
        val walletManager =  WalletManager.instance
        val finalAmount = walletManager.quantity + tips
        walletManager.quantity = finalAmount

        // Update the
        val bundle = Bundle()
        bundle.putInt(WalletResultReceiver.BUNDLE_PARAM_RESULT, finalAmount)
        resultReceiver?.send(WalletResultReceiver.RESULT_CODE_OK, bundle)
    }
}

class LocalBinder : Binder()
