package com.falcon.stackoverflow.network.results
import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.RetrofitInterface
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchResultEndPoint @Inject constructor(val retrofitInterface: RetrofitInterface){

    val TAG = "FetchResultEndPoint"

    interface Listener{
        fun onFetchSuccess(items: List<ItemNet>)
        fun onFetchFailed(e: Throwable)
    }

    @SuppressLint("CheckResult")
    fun fetch(q: String, Listener: Listener) {

        val single: Single<ResultNet> = retrofitInterface.getResult(q)

        single.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe (
            { result -> Logger.log( TAG, "onSuccess: ")
                          Listener.onFetchSuccess(result.items)
            },
            { throwable -> Logger.log( TAG, "onError: e: " + throwable.getLocalizedMessage())
                           Listener.onFetchFailed(throwable)
            }
        )

    }

}
