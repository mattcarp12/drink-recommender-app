package org.matt.drink_recommender_app.model

class Answers {
    val answerMap = mutableMapOf<String, String>()
    fun set(key: String, value: String) {
        answerMap[key] = value
    }
}