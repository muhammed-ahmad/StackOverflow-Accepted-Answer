package com.falcon.stackoverflow.screens.splash

import android.app.Application
import androidx.lifecycle.ViewModel
import java.lang.Exception

class SplashViewModel (
    val application: Application
    ) : ViewModel() {

    val TAG: String = "SplashViewModel"

    interface IsConnectedListener{
        fun onSuccess()
        fun onError(error: Exception)
    }

    fun checkConnectivity(isConnectedListener :IsConnectedListener){
        // TODO("Not yet implemented")
        var isConnected: Boolean = true
        isConnectedListener.onSuccess()

    }

}
