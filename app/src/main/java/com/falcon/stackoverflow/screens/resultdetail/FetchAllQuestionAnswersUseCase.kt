package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.RetrofitInterface
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import io.reactivex.Single
import javax.inject.Inject

class FetchAllQuestionAnswersUseCase @Inject constructor(
    val retrofitInterface: RetrofitInterface
    ){

    val TAG: String = "FetchAllQuestionAnswersUseCase"

    @SuppressLint("CheckResult")
    fun fetch(questionId: Long): Single<List<AnswerItemNet>> {
        return retrofitInterface.getAllQuestionAnswers(questionId).flatMap { answerResultNet -> Single.just(answerResultNet.items) }
    }

}
