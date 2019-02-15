package com.melih.statefuldatalayer.ui.core.di

import androidx.lifecycle.ViewModel
import com.melih.statefuldatalayer.ui.MainViewModel
import com.melih.statefuldatalayer.ui.core.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(vm: MainViewModel): ViewModel
}