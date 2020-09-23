package cn.hx.news

import android.content.Context
import cn.hx.applike.annotation.AppLike
import cn.hx.applike.api.IAppLike
import cn.hx.base.BaseApplication
import cn.hx.base.BaseDispatchingInjector

@AppLike
class NewsAppLike : IAppLike {
    override fun onCreate(context: Context) {
        val newsDispatchingInjector = BaseDispatchingInjector()
        NewsComponentHolder.newsComponent.inject(newsDispatchingInjector)
        (context as? BaseApplication)?.run {
            multiModuleAndroidInjector.addInjector(newsDispatchingInjector)
        }
    }

    override fun getPriority(): Int {
        return 5
    }

    override fun onTerminate(context: Context) {

    }
}