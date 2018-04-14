package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferPresenter (view : View) : Presenter {

    companion object {
        const val TAG = "MoneyTransferPresenter"
        const val QUANTITY_TO_ADD = 100
    }

    // TODO: Check why this is not good to have it on the init
    private val moneyTransferManager: MoneyTransferManager = MoneyTransferManager()
    private val view : View = view

    override fun addSomeMoney() {
        Log.i(TAG, "Adding $QUANTITY_TO_ADD dollars ")
        view.updateQuantity(moneyTransferManager.addMoney(QUANTITY_TO_ADD))
    }
}