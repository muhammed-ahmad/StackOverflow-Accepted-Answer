package com.falcon.stackoverflow.common
import androidx.multidex.MultiDexApplication
import com.falcon.stackoverflow.common.di.app.AppModule
import com.falcon.stackoverflow.common.di.app.DaggerAppComponent

class MyApplication : MultiDexApplication() {

    val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
