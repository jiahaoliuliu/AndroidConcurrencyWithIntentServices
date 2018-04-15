package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MoneyTransferPresenter (view : View,
                              salaryManager : SalaryManager) : Presenter {

    companion object {
        const val TAG = "MoneyTransferPresenter"
        const val SALARY_TO_ADD = 100
        const val TIPS_TO_ADD = 1
    }

    // TODO: Check why this is not good to have it on the init
    private val salaryManager: SalaryManager = salaryManager
    private val view : View = view

    init {
        periodicallyAddSalary()
        periodicallyAddTips()
    }

    /**
     * Periodically add salary
     */
    private fun periodicallyAddSalary() {
        Log.i("Thread", "Periodically adding salary. The current thread is "
                 + Thread.currentThread().id)

        Observable.interval(1000, 10000, TimeUnit.MILLISECONDS)
                // Force for the subscription to happens on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addSalary()
                })
    }

    private fun addSalary() {
        Log.i(TAG, "Adding $SALARY_TO_ADD dollars as salary")
        Log.i("Thread", "Adding Salary. The current thread is " + Thread.currentThread().id)
        salaryManager.addSalary(SALARY_TO_ADD, MoneyAddedReceiverCallback(view))
    }

    private fun periodicallyAddTips() {
        Log.i("Thread", "Periodically adding tips. The current thread is "
                + Thread.currentThread().id)
        Observable.interval(1000, 500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addTips()
                })
    }

    private fun addTips() {
        Log.i("Thread", "Periodically adding tips. The current thread is "
                + Thread.currentThread().id)
        Log.i(TAG, "Adding $TIPS_TO_ADD dollars as tip")
        salaryManager.addTips(TIPS_TO_ADD, MoneyAddedReceiverCallback(view))
    }
}

class MoneyAddedReceiverCallback (view: View): WalletResultReceiver.ResultReceiverCallBack<Int> {

    private val view = view

    override fun onSuccess(finalSalary: Int) {
        Log.i("Money", "money added $finalSalary");
        Log.i("Thread", "Updating Salary. The current thread is " + Thread.currentThread().id)
        view.updateSalary(finalSalary)
    }

    override fun onError(exception: Exception) {
        Log.e("Money", "Error", exception);
    }

}