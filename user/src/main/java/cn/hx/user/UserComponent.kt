package cn.hx.user

import cn.hx.base.AppComponent
import cn.hx.base.BaseDispatchingInjector
import dagger.Component

@UserScope
@Component(
    modules = [UserModule::class, UserBindModule::class],
    dependencies = [AppComponent::class]
)
interface UserComponent {
    fun inject(baseDispatchingInjector: BaseDispatchingInjector)
}