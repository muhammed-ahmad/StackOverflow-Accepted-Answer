package com.falcon.stackoverflow.network.resultdetail

import com.falcon.stackoverflow.network.answer.AnswerItemNet
import com.falcon.stackoverflow.network.question.QuestionItemNet

// this is a custom class, i.e it's not an api representation
data class ResultDetailNet(
    val questionItemNet: QuestionItemNet,
    val answerItemNets: List<AnswerItemNet>
)