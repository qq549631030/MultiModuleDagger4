### 组件化项目

我们在[Dagger2多模块项目依赖方式选择二](https://github.com/qq549631030/MultiModuleDagger2/blob/master/README.md)的基础上改造实现

具体实现和[dagger.android多模块项目实现一](https://github.com/qq549631030/MultiModuleDagger3/blob/master/README.md)差不多，唯一差异是MultiModuleAndroidInjector放在BaseApplication中

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

这里user,news模块初始化放在app模块中，与组件化思想不符，这里可以使用[AppLike](https://github.com/qq549631030/Applike)插件来实现

先中BaseApplication中加入AppLikeManager的onCreate和onTerminate方法

```kotlin
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
```



然后每个模块建一个AppLike，把初始化代码放在onCreate方法中

```kotlin
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
```

这样APP启动的时候每个模块的初始化都会自动执行了