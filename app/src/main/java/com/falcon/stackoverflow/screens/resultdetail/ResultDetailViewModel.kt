package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falcon.stackoverflow.base.LocalListeners
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultDetailViewModel(
        val application: Application,
        val fetchResultDetailUseCase: FetchResultDetailUseCase
    ) : ViewModel() {

//    val TAG: String = "ResultDetailViewModel"
//    lateinit var resultDetailMutableLiveData: MutableLiveData<List<ResultDetail>>
//
//    fun fetch(): Observable<String> = fetchResultDetailUseCase.fetch()
//
//    @SuppressLint("CheckResult")
//    fun getByResultId(resultDetailId: String): LiveData<List<ResultDetail>> {
//
//        if (!::resultDetailMutableLiveData.isInitialized) {
//            resultDetailMutableLiveData = MutableLiveData<List<ResultDetail>>()
//
//            fetchResultDetailUseCase.getByResultId(resultDetailId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { resultDetails -> Logger.log( TAG, "onNext: ")
//                                             resultDetailsMutableLiveData.setValue(resultDetails) },
//                            { throwable -> Logger.log( TAG, "onError: " + throwable.localizedMessage) },
//                            { Logger.log( TAG, "onComplete: ") },
//                            { disposable ->  Logger.log( TAG, "onSubscribe: ") } )
//
//        }
//        return resultDetailsMutableLiveData
//    }
//

}
