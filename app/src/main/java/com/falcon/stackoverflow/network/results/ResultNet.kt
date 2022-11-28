package com.falcon.stackoverflow.network.results;

data class ResultNet(
    val items: List<ItemNet>,
    val has_more :Boolean,
    val quota_max : Int,
    val quota_remaining : Int
)
