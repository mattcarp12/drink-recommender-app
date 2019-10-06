package org.matt.drink_recommender_app.model


data class Questions(val questionList: List<Question>) {
    fun size(): Int = questionList.size
}

/*
data class Questions(
    var currentQuestionNumber: Int
    , var keys: List<String>
    , var questionList: List<String>
    , var answers: MutableMap<String, String?>
) {

    constructor() : this(0, emptyList(), emptyList(), mutableMapOf()) {
        keys = listOf<String>(
            "gender"
            , "pets"
            , "occupation"
            , "personality"
        )

        questionList = listOf<String>(
            "What is your gender?"
            , "How many pets do you have?"
            , "What is your occupation?"
            , "What best describes your personality?"
        )
    }

    fun setAnswer(questionNumber: Int, answer: String): Unit {
        answers[keys[questionNumber]] = answer
    }

    fun getCurrentQuestionNumberLiveData(): String {
        return questionList[currentQuestionNumber]
    }

    fun next(): Boolean {
        if (currentQuestionNumber == keys.size - 1) return false
        else {
            currentQuestionNumber++
            return true
        }
    }
    fun prev(): Boolean {
        if (currentQuestionNumber == 0) return false
        else {
            currentQuestionNumber--
            return true
        }
    }

}*/
