package com.melih.statefuldatalayer.ui.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
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

    private var mutableLiveData = MutableLiveData<Int>()
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
                    repeat(10) {
//                        liveDataChannel.postValue(it)
//                        mutableLiveData.postValue(it)
                    }
                }

                async {
                    repeat(10) {
//                        liveDataChannel.postValue(it + 10)
//                        mutableLiveData.postValue(it + 10)
                    }
                }
            }

            liveDataChannel.postValue(10)

            withContext(Dispatchers.Main){
                delay(1000)
                observe(liveDataChannel){
                    Log.w("Channel1", "Received: $it")
                }
            }
        }

//        liveDataChannel.postValue(10)
//
//        observe(liveDataChannel){
//            Log.w("Channel1", "Received: $it")
//        }

        observe(mutableLiveData) {
            Log.w("Channel2", "Received: $it")
        }
    }
}
