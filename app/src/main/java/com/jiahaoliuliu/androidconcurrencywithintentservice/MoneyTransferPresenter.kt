package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferPresenter : Presenter {

    companion object {
        val TAG = "MoneyTransferPresenter"
    }

    override fun addSomeMoney() {
        Log.i(TAG, "Adding some money")
    }
}