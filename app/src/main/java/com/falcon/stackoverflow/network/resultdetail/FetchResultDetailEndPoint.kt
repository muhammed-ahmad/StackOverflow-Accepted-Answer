package com.falcon.stackoverflow.network.resultdetail
import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.RetrofitInterface
import com.falcon.stackoverflow.utils.Logger
import javax.inject.Inject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

open class FetchResultDetailEndPoint @Inject constructor(val retrofitInterface: RetrofitInterface){

    val TAG: String = "FetchResultDetailEndPoint"

    interface Listener {
        fun onFetchSuccess(resultDetailNets: List<ResultDetailNet>)
        fun onFetchFailed(e: Throwable)
    }

    @SuppressLint("CheckResult")
    open fun fetch(resultId: String, listener: Listener) {

//        val single: Single<List<ResultDetailNet>> = retrofitInterface.getResult(resultId)
//
//        single.subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(
//                    { resultDetailNets -> Logger.log(TAG , "onSuccess: ")
//                        listener.onFetchSuccess(resultDetailNets)},
//                    { throwable -> Logger.log(TAG , "onError: " + throwable.getLocalizedMessage())
//                        listener.onFetchFailed(throwable)}
//                )
    }

}
