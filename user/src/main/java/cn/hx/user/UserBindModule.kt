package cn.hx.user

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class UserBindModule {
    @ContributesAndroidInjector
    abstract fun userActivity(): UserActivity
}