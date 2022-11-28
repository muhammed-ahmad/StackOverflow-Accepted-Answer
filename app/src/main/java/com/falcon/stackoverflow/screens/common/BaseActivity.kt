package com.falcon.stackoverflow.screens.common
import androidx.appcompat.app.AppCompatActivity
import com.falcon.stackoverflow.common.MyApplication
import com.falcon.stackoverflow.common.di.activity.ActivityModule
import com.falcon.stackoverflow.common.di.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

    private val appComponent
        get() = (application as MyApplication).appComponent
    
    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

}
