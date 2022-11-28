package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.resultdetail.FetchResultDetailEndPoint
import com.falcon.stackoverflow.network.resultdetail.ResultDetailNet
import com.falcon.stackoverflow.screens.models.ResultDetail
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Observable
import javax.inject.Inject

class FetchResultDetailUseCase @Inject constructor(
    val fetchResultDetailEndPoint: FetchResultDetailEndPoint
    ){

    val TAG: String = "FetchResultDetailUseCase"

    @SuppressLint("CheckResult")
    fun fetch(): Observable<String>{

        return Observable.create{
                emitter ->

                    val resultId: String = ""

                    fetchResultDetailEndPoint.fetch(resultId, object: FetchResultDetailEndPoint.Listener {

                        override fun onFetchSuccess(resultDetailNets: List<ResultDetailNet>) {
                            Logger.log(TAG , " onFetchSuccess: " + resultDetailNets.size)
                            fromNetListToLocal(resultDetailNets)
                            emitter.onNext("upsert_completed")
                            emitter.onComplete()
                        }

                        override fun onFetchFailed(e: Throwable) {
                            Logger.log(TAG, "onFetchFailed: error: " + e.getLocalizedMessage())
                            emitter.onError(e)
                        }
                    })
            }
    }


    fun fromNetToLocal(resultDetailNet: ResultDetailNet): ResultDetail =
        ResultDetail(
                resultDetailNet.id,
                resultDetailNet.resultId,
                resultDetailNet.name,
                resultDetailNet.imageUrl
                )


    fun fromNetListToLocal(resultDetailNets: List<ResultDetailNet>) {
        for (current in resultDetailNets) {
            fromNetToLocal(current)
        }
    }

}
