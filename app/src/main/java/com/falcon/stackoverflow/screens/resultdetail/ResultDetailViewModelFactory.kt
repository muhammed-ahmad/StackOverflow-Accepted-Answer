package com.falcon.stackoverflow.screens.resultdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultDetailViewModelFactory(
    var application: Application,
    var fetchResultDetailUseCase: FetchResultDetailUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(application, fetchResultDetailUseCase) as T
    }
}