package com.falcon.stackoverflow.common.di.app
import com.falcon.stackoverflow.common.di.activity.ActivityComponent
import com.falcon.stackoverflow.common.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}
