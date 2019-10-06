package org.matt.drink_recommender_app.model


data class Questions(val questionList: List<Question>) {
    fun size(): Int = questionList.size
}
