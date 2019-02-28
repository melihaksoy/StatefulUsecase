package com.melih.statefuldatalayer.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.melih.statefuldatalayer.R
import com.melih.statefuldatalayer.databinding.MainBinding
import com.melih.statefuldatalayer.ui.MainViewModel
import com.melih.statefuldatalayer.ui.core.base.ViewModelFactory
import com.melih.statefuldatalayer.ui.core.extensions.observe
import com.melih.statefuldatalayer.ui.core.livedata.LiveDataChannel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.*
import javax.inject.Inject

@ObsoleteCoroutinesApi
class MainActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private var liveDataChannel = LiveDataChannel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]

        binding.viewModel = viewModel

        @Suppress("DeferredResultUnused")
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Default) {
                async {
                    repeat(5) {
                        liveDataChannel.postValue(it)
                        delay(35)
                    }
                }

                async {
                    repeat(5) {
                        liveDataChannel.postValue(it + 5)
                        delay(25)
                    }
                }
            }
        }

        observe(liveDataChannel){
            println("Received: $it")
        }
    }
}
