package cn.hx.multimoduledagger

import cn.hx.base.BaseApplication
import cn.hx.base.BaseDispatchingInjector
import cn.hx.news.NewsComponentHolder
import cn.hx.user.UserComponentHolder

class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //这部份应当各种模块中初始化（后期用插件实现）
        val userDispatchingInjector = BaseDispatchingInjector()
        UserComponentHolder.userComponent.inject(userDispatchingInjector)
        multiModuleAndroidInjector.addInjector(userDispatchingInjector)
        val newsDispatchingInjector = BaseDispatchingInjector()
        NewsComponentHolder.newsComponent.inject(newsDispatchingInjector)
        multiModuleAndroidInjector.addInjector(newsDispatchingInjector)

    }
}