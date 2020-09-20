package cn.hx.user

import cn.hx.base.BaseApplication

object UserComponentHolder {
    val userComponent: UserComponent by lazy {
        DaggerUserComponent.builder()
            .appComponent(BaseApplication.instance.appComponent)
            .build()
    }
}