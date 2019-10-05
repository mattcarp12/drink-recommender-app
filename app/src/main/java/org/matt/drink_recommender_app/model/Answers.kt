package org.matt.drink_recommender_app.model

class Answers {
    val answers = mutableMapOf<String, String>()
    fun set(key: String, value: String) {
        answers[key] = value
    }
}