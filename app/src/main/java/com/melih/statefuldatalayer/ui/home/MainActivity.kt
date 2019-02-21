package com.melih.statefuldatalayer.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.melih.statefuldatalayer.R
import com.melih.statefuldatalayer.databinding.MainBinding
import com.melih.statefuldatalayer.ui.MainViewModel
import com.melih.statefuldatalayer.ui.core.base.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

@ObsoleteCoroutinesApi
class MainActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]

        binding.viewModel = viewModel
    }
}
