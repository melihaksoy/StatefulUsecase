package com.melih.statefuldatalayer

import com.melih.statefuldatalayer.ui.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .create(this)

    override fun onCreate() {
        super.onCreate()
    }
}