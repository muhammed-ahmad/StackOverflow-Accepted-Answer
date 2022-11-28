package com.falcon.stackoverflow.screens.splash

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplashViewModelFactory(
    var application: Application
): ViewModelProvider.Factory {

    override fun <T :ViewModel> create(modelClass:  Class<T>) : T {
        return SplashViewModel(application) as T
    }
}