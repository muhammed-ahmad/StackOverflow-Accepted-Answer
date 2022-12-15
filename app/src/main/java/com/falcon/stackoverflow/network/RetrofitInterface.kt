package com.falcon.stackoverflow.network
import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.network.answer.AnswerResultNet
import com.falcon.stackoverflow.network.question.QuestionResultNet
import com.falcon.stackoverflow.network.results.ResultNet
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitInterface {

    @GET("search/advanced?order=desc&sort=activity&accepted=True&site=stackoverflow")
    fun  getResult(@Query("q") q: String ): Single<ResultNet>

    @GET("answers/{answer_id}?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun  getAcceptedAnswer(@Path("answer_id") answerId: Long ): Single<AnswerResultNet>

    @GET("questions/{question_id}?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun  getQuestionBody(@Path("question_id") questionId: Long ): Single<QuestionResultNet>

    @GET("questions/{question_id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun  getAllQuestionAnswers(@Path("question_id") questionId: Long ): Single<AnswerResultNet>

}
