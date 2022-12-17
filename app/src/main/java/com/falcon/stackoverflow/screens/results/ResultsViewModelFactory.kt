package com.falcon.stackoverflow.screens.results

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falcon.stackoverflow.screens.common.ConnectivityChecker

class ResultsViewModelFactory(
    var application: Application,
    val connectivityChecker: ConnectivityChecker,
    var fetchResultUseCase: FetchResultUseCase,
    var fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultViewModel(application, connectivityChecker, fetchResultUseCase, fetchAcceptedAnswerUseCase) as T
    }
}