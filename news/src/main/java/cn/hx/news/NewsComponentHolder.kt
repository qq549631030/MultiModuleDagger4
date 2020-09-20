package cn.hx.news

import cn.hx.base.BaseApplication

object NewsComponentHolder {
    val newsComponent: NewsComponent by lazy {
        DaggerNewsComponent.builder()
            .appComponent(BaseApplication.instance.appComponent)
            .build()
    }
}