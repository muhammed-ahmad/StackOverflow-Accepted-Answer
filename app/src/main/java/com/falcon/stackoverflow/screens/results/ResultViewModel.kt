package com.falcon.stackoverflow.screens.results

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falcon.stackoverflow.screens.common.ConnectivityChecker
import com.falcon.stackoverflow.screens.models.AnswerItem
import com.falcon.stackoverflow.screens.models.Item
import com.falcon.stackoverflow.screens.models.RenderedItem
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultViewModel(
    val application: Application,
    val connectivityChecker: ConnectivityChecker,
    val fetchResultUseCase: FetchResultUseCase,
    val fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
    ) : ViewModel() {

    val TAG: String = "ResultViewModel"
    lateinit var itemsMutableLiveData: MutableLiveData<List<RenderedItem>>

    fun checkConnectivity() : Single<Boolean> = connectivityChecker.checkConnectivity()

    @SuppressLint("CheckResult")
    fun fetch(query: String): LiveData<List<RenderedItem>> {

        var _items: List<Item> = mutableListOf()
        if (!::itemsMutableLiveData.isInitialized) {
            itemsMutableLiveData = MutableLiveData<List<RenderedItem>>()

            fetchResultUseCase.fetch(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable{
                    items -> _items = items
                             items
                }
                .flatMap {
                    item -> fetchAcceptedAnswerUseCase.fetch(item.accepted_answer_id)
                }
                .toList()
                .subscribe(
                    { answerItems -> Logger.log( TAG, "onNext: " + answerItems.size + " , " + _items.size)
                        var renderedItems: List<RenderedItem> = prepareRenderedItems(_items, answerItems)
                        itemsMutableLiveData.postValue(renderedItems)
                    },
                    { throwable -> Logger.log( TAG, "onError: " + throwable.localizedMessage) },
                )
        }
        return itemsMutableLiveData
    }

    fun prepareRenderedItems(items: List<Item>, answerItems: List<AnswerItem>) : List<RenderedItem> {
        var renderedItems = mutableListOf<RenderedItem>()
        for (i in items.indices){
            renderedItems.add(prepareRenderedItem(items[i], answerItems[i]))
        }
        return renderedItems
    }

    fun prepareRenderedItem(item: Item, answerItem: AnswerItem) : RenderedItem = RenderedItem(
        item.accepted_answer_id,
        item.question_id,
        item.link,
        item.title,
        answerItem.body,
        answerItem.score,
    )

}
