package com.falcon.stackoverflow.network.results
import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.RetrofitInterface
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.network.answer.AnswerResultNet
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchAcceptedAnswerEndPoint @Inject constructor(val retrofitInterface: RetrofitInterface){

    val TAG = "FetchResultEndPoint"

    interface Listener{
        fun onFetchSuccess(items: AnswerItemNet)
        fun onFetchFailed(e: Throwable)
    }

    @SuppressLint("CheckResult")
    fun fetch(answerId: Long, Listener: Listener) {

        val single: Single<AnswerResultNet> = retrofitInterface.getAcceptedAnswer(answerId)

        single.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe (
            { result -> Logger.log( TAG, "onSuccess: ")
                Listener.onFetchSuccess(result.items[0])
            },
            { throwable -> Logger.log( TAG, "onError: e: " + throwable.getLocalizedMessage())
                Listener.onFetchFailed(throwable)
            }
        )

    }

}
