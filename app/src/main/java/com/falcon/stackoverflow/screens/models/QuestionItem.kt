package com.falcon.stackoverflow.screens.models

data class QuestionItem(
    val body: String,
    val score: Int,
    val question_id: Long,
    val link: String,
    val title: String
)
