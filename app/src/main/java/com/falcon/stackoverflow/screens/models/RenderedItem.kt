package com.falcon.stackoverflow.screens.models

data class RenderedItem(
    val accepted_answer_id: Long,
    val question_id: Long,
    val link: String,
    val title: String,
    val answerBody: String,
    val answerScore: Int,
)
