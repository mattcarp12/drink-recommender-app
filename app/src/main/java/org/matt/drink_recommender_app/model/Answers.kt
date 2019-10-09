package org.matt.drink_recommender_app.model

data class Answers(val answerMap: MutableMap<String, String>) {

    constructor() : this(answerMap = mutableMapOf<String, String>()) { }

    fun set(key: String, value: String) {
        answerMap[key] = value
    }

    fun get(key: String): String? = if(answerMap.containsKey(key)) answerMap.get(key) else null
}