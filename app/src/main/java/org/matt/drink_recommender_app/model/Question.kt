package org.matt.drink_recommender_app.model

data class Question(val questionName: String,
                    val questionText: String,
                    val responses: List<String>)