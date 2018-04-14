package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

class WalletResultReceiver<T>(handler: Handler?) : ResultReceiver(handler) {

    companion object {
        const val RESULT_CODE_OK = 200
        const val RESULT_CODE_ERROR = 404
        const val BUNDLE_PARAM_RESULT = "Result"
        const val BUNDLE_PARAM_EXCEPTION = "Exception"
        const val INTENT_PARAM_RESULT_RECEIVER = "Result receiver"
    }

    var receiver : ResultReceiverCallBack<T>? = null

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (resultCode == RESULT_CODE_OK) {
            receiver?.onSuccess(resultData?.getSerializable(BUNDLE_PARAM_RESULT) as T);
        } else {
            receiver?.onError(resultData?.getSerializable(BUNDLE_PARAM_EXCEPTION) as Exception)
        }
    }

    interface ResultReceiverCallBack<T> {
        fun onSuccess(data: T)
        fun onError(exception: Exception)
    }
}