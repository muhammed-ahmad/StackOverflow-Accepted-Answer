package com.falcon.stackoverflow.screens.results

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falcon.stackoverflow.screens.models.AnswerItem
import com.falcon.stackoverflow.screens.models.Item
import com.falcon.stackoverflow.screens.models.RenderedItem
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultViewModel(
        val application: Application,
        val fetchResultUseCase: FetchResultUseCase,
        val fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
    ) : ViewModel() {

    val TAG: String = "ResultViewModel"
    lateinit var itemsMutableLiveData: MutableLiveData<List<Item>>

    interface ResultListener{
        fun onSuccess(items: List<RenderedItem>)
    }

    @SuppressLint("CheckResult")
    fun fetch(query: String, resultListener: ResultListener) {

        var _items: List<Item> = mutableListOf()
        if (!::itemsMutableLiveData.isInitialized) {
            itemsMutableLiveData = MutableLiveData<List<Item>>()

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
                        resultListener.onSuccess(renderedItems)
                    },
                    { throwable -> Logger.log( TAG, "onError: " + throwable.localizedMessage) },
                )

        }
    }

//    @SuppressLint("CheckResult")
//    fun fetch(query: String): LiveData<List<Item>> {
//
//        if (!::itemsMutableLiveData.isInitialized) {
//            itemsMutableLiveData = MutableLiveData<List<Item>>()
//
//            fetchResultUseCase.fetch(query)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { items -> Logger.log( TAG, "onNext: ")
//                                             itemsMutableLiveData.setValue(items) },
//                            { throwable -> Logger.log( TAG, "onError: " + throwable.localizedMessage) },
//                            { Logger.log( TAG, "onComplete: ") },
//                            { disposable ->  Logger.log( TAG, "onSubscribe: ") } )
//
//        }
//        return itemsMutableLiveData
//    }

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
