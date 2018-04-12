package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferPresenter : Presenter {

    companion object {
        @JvmField
        val TAG = "MoneyTransferPresenter"
    }

    init {
        val moneyTransferManager  = MoneyTransferManager()
    }

    override fun addSomeMoney() {
        Log.i(TAG, "Adding some money")
    }
}