package cn.hx.base

import dagger.android.AndroidInjector

class MultiModuleAndroidInjector : AndroidInjector<Any> {

    private val injectors = mutableListOf<HasDispatchingInjector>()

    fun addInjector(injector: HasDispatchingInjector) {
        injectors.add(injector)
    }

    override fun inject(instance: Any) {
        val wasInjected = injectors.any { it.dispatchingAndroidInjector().maybeInject(instance) }
        if (!wasInjected) {
            throw IllegalArgumentException("injection failed")
        }
    }
}