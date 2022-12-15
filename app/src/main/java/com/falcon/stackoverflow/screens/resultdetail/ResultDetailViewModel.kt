package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.network.question.QuestionItemNet
import com.falcon.stackoverflow.network.resultdetail.ResultDetailNet
import com.falcon.stackoverflow.screens.models.AnswerItem
import com.falcon.stackoverflow.screens.models.QuestionItem
import com.falcon.stackoverflow.screens.models.ResultDetail
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultDetailViewModel(
        val application: Application,
        val fetchResultDetailUseCase: FetchResultDetailUseCase
    ) : ViewModel() {

    val TAG: String = "ResultDetailViewModel"
    lateinit var resultDetailMutableLiveData: MutableLiveData<ResultDetail>

    @SuppressLint("CheckResult")
    fun getQuestionData(questionId: Long): LiveData<ResultDetail> {

        if (!::resultDetailMutableLiveData.isInitialized) {
            resultDetailMutableLiveData = MutableLiveData<ResultDetail>()

            fetchResultDetailUseCase.fetch(questionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { resultDetailNet -> Logger.log( TAG, "onSuccess: ")
                                    resultDetailMutableLiveData.setValue(fromNetToLocal(resultDetailNet)) },
                            { throwable -> Logger.log( TAG, "onError: " + throwable.localizedMessage) },
                             )

        }
        return resultDetailMutableLiveData
    }


    fun fromNetToLocal(resultDetailNet: ResultDetailNet): ResultDetail =
        ResultDetail(
            fromQuestionItemNetToLocal(resultDetailNet.questionItemNet),
            fromAnswerItemNetListToLocal(resultDetailNet.answerItemNets)
        )


    fun fromQuestionItemNetToLocal(questionItemNet: QuestionItemNet) = QuestionItem (
        questionItemNet.body,
        questionItemNet.score,
        questionItemNet.question_id,
        questionItemNet.link,
        questionItemNet.title
    )


    fun fromAnswerItemNetToLocal(answerItemNet: AnswerItemNet): AnswerItem =
        AnswerItem(
            answerItemNet.body,
            answerItemNet.score,
        )

    fun fromAnswerItemNetListToLocal(answerItemNets: List<AnswerItemNet>): List<AnswerItem>{
        var answerItems = mutableListOf<AnswerItem>()

        for (current in answerItemNets) {
            answerItems.add(fromAnswerItemNetToLocal(current))
        }
        return answerItems
    }

}
