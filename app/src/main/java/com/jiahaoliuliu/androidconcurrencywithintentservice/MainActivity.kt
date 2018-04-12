package com.jiahaoliuliu.androidconcurrencywithintentservice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jiahaoliuliu.androidconcurrencywithintentservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = MoneyTransferPresenter()
    }
}