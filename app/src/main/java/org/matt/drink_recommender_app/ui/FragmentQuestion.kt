package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_question.*
import org.matt.drink_recommender_app.R
import org.matt.drink_recommender_app.viewmodel.ViewModelQuestions

class FragmentQuestion() : Fragment() {

    lateinit var model: ViewModelQuestions
    var TAG: String = "FragmentQuestion"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        model = activity?.run {
            ViewModelProviders.of(this)[ViewModelQuestions::class.java]
        } ?: throw Exception("Invalid Activity")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize textviews with first question (number 1)
        question_title.text = getString(R.string.questionNumber, model.current)
        question_text.text = model.questions.get(model.keys.get(model.current - 1))

        // Set button listeners
        next_button.setOnClickListener(nextButtonListener)
        back_button.setOnClickListener(backButtonListener)

    }

    private val nextButtonListener = View.OnClickListener {
        if (model.current == model.questions.size) {
            findNavController().navigate(R.id.action_fragmentQuestion_to_fragmentResult)
        } else {
            question_title.text = getString(R.string.questionNumber, ++model.current)
            question_text.text = model.questions.get(model.keys.get(model.current - 1))
            model.answers[model.keys.get(model.current - 1)] = "foo"
        }
    }

    private val backButtonListener = View.OnClickListener {
        if (model.current == 1) {
            // TODO : Popup asking if want to return to start screen?
        } else {
            question_title.text = getString(R.string.questionNumber, --model.current)
            question_text.text = model.questions.get(model.keys.get(model.current - 1))
        }
    }

    private fun getQuestionAnswer(view: View): String {
        if (view is RadioGroup) {
            return "foo"
        } else if (view is EditText) {
            return "bar"
        } else if (view is Spinner) {
            return "foobar"
        } else {
            return ""
        }
    }

}