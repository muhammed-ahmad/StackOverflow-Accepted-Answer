package com.falcon.stackoverflow.screens.results

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultsViewModelFactory(
    var application: Application,
    var fetchResultUseCase: FetchResultUseCase,
    var fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultViewModel(application, fetchResultUseCase, fetchAcceptedAnswerUseCase) as T
    }
}