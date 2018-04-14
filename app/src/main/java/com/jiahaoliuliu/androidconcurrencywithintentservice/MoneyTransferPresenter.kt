package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferPresenter : Presenter {

    companion object {
        @JvmField
        val TAG = "MoneyTransferPresenter"
        @JvmField
        val QUANTITY_TO_ADD = 100
    }

    private val moneyTransferManager: MoneyTransferManager = MoneyTransferManager()
    // TODO: move this to the init block
    private lateinit var view : View

    init {
    }

    override fun setView(view: View) {
        this.view = view
    }

    override fun addSomeMoney() {
        Log.i(TAG, "Adding 10 dollars ")
        val finalQuantity = moneyTransferManager.addMoney(QUANTITY_TO_ADD)
        view.updateQuantity(finalQuantity)
    }
}