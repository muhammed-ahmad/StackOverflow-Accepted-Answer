package com.falcon.stackoverflow.screens.results

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.results.FetchResultEndPoint
import com.falcon.stackoverflow.network.results.ItemNet
import com.falcon.stackoverflow.network.results.ResultNet
import com.falcon.stackoverflow.screens.models.Item
import com.falcon.stackoverflow.screens.models.Result
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Observable
import javax.inject.Inject

class FetchResultUseCase @Inject constructor(
        val fetchResultEndPoint: FetchResultEndPoint
    ){

    val TAG: String = "FetchResultUseCase"

    @SuppressLint("CheckResult")
    fun fetch(query: String): Observable<List<Item>>{

        return Observable.create{
                emitter ->
                    fetchResultEndPoint.fetch(query, object: FetchResultEndPoint.Listener {

                        override fun onFetchSuccess(itemNets: List<ItemNet>) {
                            Logger.log(TAG , " onFetchSuccess: " + itemNets.size)
                            emitter.onNext(fromNetListToLocal(itemNets))
                            emitter.onComplete()
                        }

                        override fun onFetchFailed(e: Throwable) {
                            Logger.log(TAG, "onFetchFailed: error: " + e.getLocalizedMessage())
                            emitter.onError(e)
                        }
                    })
            }
    }


    fun fromNetToLocal(itemNet: ItemNet): Item =
        Item(
            itemNet.accepted_answer_id,
            itemNet.question_id,
            itemNet.link,
            itemNet.title
        )


    fun fromNetListToLocal(itemNets: List<ItemNet>): List<Item>{
        var items = mutableListOf<Item>()

        for (current in itemNets) {
            items.add(fromNetToLocal(current))
        }
        return items
    }

}
