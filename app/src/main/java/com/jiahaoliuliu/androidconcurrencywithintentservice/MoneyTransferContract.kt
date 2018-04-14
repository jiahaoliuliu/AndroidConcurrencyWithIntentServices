package com.jiahaoliuliu.androidconcurrencywithintentservice

interface View {
    /**
     * Update the final money quantity
     */
    fun updateQuantity(finalQuantity : Int)
}

interface Presenter {

    /**
     * Set the view.
     * TODO: This should be replaced by the primary constructor
     */
    fun setView(view: View)

    /**
     * Add some money to the account
     */
    fun addSomeMoney()
}

interface Model {

}