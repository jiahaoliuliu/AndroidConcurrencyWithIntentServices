package com.jiahaoliuliu.androidconcurrencywithintentservice

interface View {
    /**
     * Update the final money quantity
     */
    fun updateQuantity(finalQuantity : Int)
}

interface Presenter {

    /**
     * Add some money to the account
     */
    fun addSomeMoney()
}

interface Model {

}