package cn.hx.news

import cn.hx.base.AppComponent
import cn.hx.base.BaseDispatchingInjector
import dagger.Component

@NewsScope
@Component(
    modules = [NewsModule::class, NewsBindModule::class],
    dependencies = [AppComponent::class]
)
interface NewsComponent {
    fun inject(baseDispatchingInjector: BaseDispatchingInjector)
}