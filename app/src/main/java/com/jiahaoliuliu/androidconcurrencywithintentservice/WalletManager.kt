package com.jiahaoliuliu.androidconcurrencywithintentservice

class WalletManager private constructor(){

    private object Holder {
        val INSTANCE = WalletManager()
    }

    companion object {
        val instance : WalletManager by lazy {
            Holder.INSTANCE
        }
    }

    var quantity = 0

}