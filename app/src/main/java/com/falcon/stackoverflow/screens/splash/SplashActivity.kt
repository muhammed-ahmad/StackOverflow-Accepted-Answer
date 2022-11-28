package com.falcon.stackoverflow.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.falcon.stackoverflow.databinding.ActivitySplashBinding
import com.falcon.stackoverflow.screens.common.BaseActivity
import com.falcon.stackoverflow.screens.common.ScreensNavigator
import com.falcon.stackoverflow.utils.Logger
import javax.inject.Inject
import io.reactivex.plugins.RxJavaPlugins
import java.lang.Exception

class SplashActivity : BaseActivity() {

    val TAG: String = "SplashActivity"
    lateinit var binding: ActivitySplashBinding 

    @Inject lateinit var splashViewModel: SplashViewModel 
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var layoutInflator: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflator)
        setContentView(binding.root)
        startResultsActivity()
    }

    fun startResultsActivity() {
        RxJavaPlugins.setErrorHandler{e -> { }}

        splashViewModel.checkConnectivity(object : SplashViewModel.IsConnectedListener{

            override fun onSuccess() {
                    screensNavigator.toResultsActivity()
                    Logger.log( TAG,"onSuccess: ")
                    //binding.progressBar.setProgress(100)
                    //binding.progressBar.setVisibility(View.INVISIBLE)
                }
            override fun onError(e: Exception) {
                    binding.progressBar.setVisibility(View.INVISIBLE)
                    val error: String? = e.localizedMessage
                    Logger.log( TAG,"onFailed: " + error)
                    binding.errorTxt.setText(error)
                }
        })
    }
}