package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log

class MoneyTransferPresenter (view : View,
                              addMoneyManager : AddMoneyManager) : Presenter {

    companion object {
        const val TAG = "MoneyTransferPresenter"
        const val QUANTITY_TO_ADD = 100
    }

    // TODO: Check why this is not good to have it on the init
    private val addMoneyManager: AddMoneyManager = addMoneyManager
    private val view : View = view

    override fun addSomeMoney() {
        Log.i(TAG, "Adding $QUANTITY_TO_ADD dollars ")
        addMoneyManager.addMoney(QUANTITY_TO_ADD, MoneyAddedReceiverCallback(view))
    }
}

class MoneyAddedReceiverCallback (view: View): WalletResultReceiver.ResultReceiverCallBack<Int> {

    private val view = view

    override fun onSuccess(finalAmount: Int) {
        Log.i("Money", "Money added $finalAmount");
        view.updateQuantity(finalAmount)
    }

    override fun onError(exception: Exception) {
        Log.e("Money", "Error", exception);
    }

}