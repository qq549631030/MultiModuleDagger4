package cn.hx.base

import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class BaseDispatchingInjector : HasDispatchingInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun dispatchingAndroidInjector(): DispatchingAndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}