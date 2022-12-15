package com.falcon.stackoverflow.screens.resultdetail

import android.annotation.SuppressLint
import com.falcon.stackoverflow.network.resultdetail.ResultDetailNet
import io.reactivex.Single
import javax.inject.Inject

class FetchResultDetailUseCase @Inject constructor(
        var fetchQuestionBodyUseCase: FetchQuestionBodyUseCase,
        var fetchAllQuestionAnswersUseCase: FetchAllQuestionAnswersUseCase
    ){

    val TAG: String = "FetchResultDetailUseCase"

    @SuppressLint("CheckResult")
    fun fetch(questionId: Long): Single<ResultDetailNet> {
        return Single.zip(
            fetchQuestionBodyUseCase.fetch(questionId),
            fetchAllQuestionAnswersUseCase.fetch(questionId),
            { questionItemNet, answerItemNets -> ResultDetailNet(questionItemNet, answerItemNets) }
        )
    }

}
