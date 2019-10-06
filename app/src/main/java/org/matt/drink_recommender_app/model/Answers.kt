package org.matt.drink_recommender_app.model

class Answers {
    val answerMap = mutableMapOf<String, String>()

    fun set(key: String, value: String) {
        answerMap[key] = value
    }

    fun get(key: String): String? = if(answerMap.containsKey(key)) answerMap.get(key) else null
}