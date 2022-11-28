package com.falcon.stackoverflow.screens.results

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.network.results.FetchAcceptedAnswerEndPoint
import com.falcon.stackoverflow.screens.models.AnswerItem
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Observable
import javax.inject.Inject

class FetchAcceptedAnswerUseCase @Inject constructor(
        val fetchAcceptedAnswerEndPoint: FetchAcceptedAnswerEndPoint
    ){

    val TAG: String = "FetchAcceptedAnswerUseCase"

    @SuppressLint("CheckResult")
    fun fetch(answerId: Long): Observable<AnswerItem>{

        return Observable.create{
                emitter ->
            fetchAcceptedAnswerEndPoint.fetch(answerId, object: FetchAcceptedAnswerEndPoint.Listener {

                        override fun onFetchSuccess(answerItem: AnswerItemNet) {
                            Logger.log(TAG , " onFetchSuccess: ")
                            emitter.onNext(fromNetToLocal(answerItem))
                            emitter.onComplete()
                        }

                        override fun onFetchFailed(e: Throwable) {
                            Logger.log(TAG, "onFetchFailed: error: " + e.getLocalizedMessage())
                            emitter.onError(e)
                        }
                    })
            }
    }


    fun fromNetToLocal(answerItemNet: AnswerItemNet): AnswerItem =
        AnswerItem(
            answerItemNet.body,
            answerItemNet.score,
        )

//    fun fromNetListToLocal(answerItemNets: List<AnswerItemNet>): List<AnswerItem>{
//        var answerItems = mutableListOf<AnswerItem>()
//
//        for (current in answerItemNets) {
//            answerItems.add(fromNetToLocal(current))
//        }
//        return answerItems
//    }

}
