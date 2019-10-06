package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_question.*
import org.matt.drink_recommender_app.R
import org.matt.drink_recommender_app.viewmodel.ViewModelQuestions

class FragmentQuestion : Fragment() {

    lateinit var viewModel: ViewModelQuestions
    var TAG: String = "FragmentQuestion"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_question, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[ViewModelQuestions::class.java]
        } ?: throw Exception("Invalid Activity")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Subscribe to live data in view model
        */
        viewModel.currentQuestionNumberLiveData.observe(viewLifecycleOwner, Observer {
            question_title.text = getString(R.string.questionNumber, it + 1)
        })

        viewModel.currentQuestionLiveData.observe(viewLifecycleOwner, Observer {
            question_text.text = it
        })

        /*
        * Set button listeners
        */
        next_button.setOnClickListener({nextButtonClick()})
        back_button.setOnClickListener({backButtonClick()})

    }

    fun nextButtonClick() {
        if(!viewModel.nextQuestion())
            findNavController().navigate(R.id.action_fragmentQuestion_to_fragmentResult)
    }

    fun backButtonClick() {
        viewModel.prevQuestion()
    }
}