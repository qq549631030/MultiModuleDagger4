### 组件化项目

我们在[Dagger2多模块项目依赖方式选择二]()的基础上改造实现

具体实现和[dagger.android多模块项目实现一]()差不多，唯一差异是MultiModuleAndroidInjector放在BaseApplication中

```kotlin
open class BaseApplication : Application(), HasAndroidInjector {

    val multiModuleAndroidInjector = MultiModuleAndroidInjector()

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return multiModuleAndroidInjector
    }
}
```

然后AppApplication

```kotlin
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
```