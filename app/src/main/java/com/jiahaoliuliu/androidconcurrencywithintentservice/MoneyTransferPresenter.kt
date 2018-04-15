package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MoneyTransferPresenter (view : View,
                              addMoneyManager : AddMoneyManager) : Presenter {

    companion object {
        const val TAG = "MoneyTransferPresenter"
        const val QUANTITY_TO_ADD = 100
    }

    // TODO: Check why this is not good to have it on the init
    private val addMoneyManager: AddMoneyManager = addMoneyManager
    private val view : View = view

    init {
        periodicallyAddMoney()
    }

    /**
     * Periodically add some money
     */
    private fun periodicallyAddMoney() {
        Observable.interval(1000, 3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<Long> {
                    addSomeMoney()
                })
    }

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