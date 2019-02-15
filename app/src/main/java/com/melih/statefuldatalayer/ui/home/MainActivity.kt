package com.melih.statefuldatalayer.ui.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.melih.statefuldatalayer.R
import com.melih.statefuldatalayer.databinding.MainBinding
import com.melih.statefuldatalayer.ui.MainViewModel
import com.melih.statefuldatalayer.ui.core.base.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]

        binding.viewModel = viewModel

        Log.i("Async", "Started observing")
        viewModel.result.observe(this, Observer {
            Log.i("Async", "Data: ${it.javaClass.simpleName}")
            it.handleResult(viewModel::handleSuccess, viewModel::handleFailure)
        })
    }
}
