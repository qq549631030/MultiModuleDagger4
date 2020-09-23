package cn.hx.base

import android.app.Application
import cn.hx.applike.api.AppLikeManager
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

open class BaseApplication : Application(), HasAndroidInjector {

    val multiModuleAndroidInjector = MultiModuleAndroidInjector()

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.factory().create(this)
        AppLikeManager.onCreate(this)
    }

    override fun onTerminate() {
        AppLikeManager.onTerminate(this)
        super.onTerminate()
    }

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return multiModuleAndroidInjector
    }
}