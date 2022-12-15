package com.falcon.stackoverflow.network.question

data class QuestionItemNet(
    val body: String,
    val score: Int,
    val question_id: Long,
    val link: String,
    val title: String
)
