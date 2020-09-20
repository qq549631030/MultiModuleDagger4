package cn.hx.base

import dagger.android.DispatchingAndroidInjector

interface HasDispatchingInjector {
    fun dispatchingAndroidInjector(): DispatchingAndroidInjector<Any>
}