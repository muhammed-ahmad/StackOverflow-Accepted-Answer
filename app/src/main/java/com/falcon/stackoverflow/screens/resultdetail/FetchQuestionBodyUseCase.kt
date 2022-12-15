package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.RetrofitInterface
import com.falcon.stackoverflow.network.question.QuestionItemNet
import io.reactivex.Single
import javax.inject.Inject

class FetchQuestionBodyUseCase @Inject constructor(
    val retrofitInterface: RetrofitInterface
    ){

    val TAG: String = "FetchQuestionBodyUseCase"

    @SuppressLint("CheckResult")
    fun fetch(questionId: Long): Single<QuestionItemNet> {
        return retrofitInterface.getQuestionBody(questionId).flatMap { questionResultNet -> Single.just(questionResultNet.items[0]) }
    }

}
