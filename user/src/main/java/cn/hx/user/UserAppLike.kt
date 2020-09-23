package cn.hx.user

import android.content.Context
import cn.hx.applike.annotation.AppLike
import cn.hx.applike.api.IAppLike
import cn.hx.base.BaseApplication
import cn.hx.base.BaseDispatchingInjector

@AppLike
class UserAppLike : IAppLike {
    override fun onCreate(context: Context) {
        val userDispatchingInjector = BaseDispatchingInjector()
        UserComponentHolder.userComponent.inject(userDispatchingInjector)
        (context as? BaseApplication)?.run {
            multiModuleAndroidInjector.addInjector(
                userDispatchingInjector
            )
        }
    }

    override fun getPriority(): Int {
        return 5
    }

    override fun onTerminate(context: Context) {

    }
}