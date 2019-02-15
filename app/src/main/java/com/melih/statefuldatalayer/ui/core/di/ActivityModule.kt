package com.melih.statefuldatalayer.ui.core.di

import com.melih.statefuldatalayer.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}