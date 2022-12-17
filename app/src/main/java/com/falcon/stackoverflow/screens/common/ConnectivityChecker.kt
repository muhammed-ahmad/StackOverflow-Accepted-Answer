package com.falcon.stackoverflow.screens.common

import android.app.Application
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class ConnectivityChecker @Inject constructor (
    val application: Application
) {

    val TAG: String = "ConnectivityChecker"

    // TODO("Not yet implemented")
    fun checkConnectivity() : Single<Boolean>{
        return Single.just(true)
    }
}