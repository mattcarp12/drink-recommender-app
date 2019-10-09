package org.matt.drink_recommender_app.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_question.*
import org.matt.drink_recommender_app.R
import org.matt.drink_recommender_app.viewmodel.AppViewModel
import java.time.LocalDate

class FragmentQuestion : Fragment() {

    lateinit var viewModel: AppViewModel
    var TAG: String = "FragmentQuestion"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_question, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[AppViewModel::class.java]
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

        viewModel.currentQuestionResponsesLiveData.observe(viewLifecycleOwner, Observer {
            setRadioButtons(it)
        })

        /*
        * Set button listeners
        */
        next_button.setOnClickListener({ nextButtonClick() })
        back_button.setOnClickListener({ backButtonClick() })
        radio_group.setOnCheckedChangeListener({ radioGroup: RadioGroup, i: Int ->
            val rb: RadioButton = radioGroup.findViewById(i) as RadioButton
            onRadioButtonSelect(rb.text as String)
        })
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun nextButtonClick() {
        next_button.isEnabled = false
        if (!viewModel.nextQuestion()) {
            viewModel.setAnswer("dayofweek", (LocalDate.now().dayOfWeek.toString()).toLowerCase())
            findNavController().navigate(R.id.action_fragmentQuestion_to_fragmentResult)
        }

    }

    fun backButtonClick() {
        viewModel.prevQuestion()
    }

    fun setRadioButtons(responses: List<String>) {
        radio_group.removeAllViews()
        val answer: String? = viewModel.getAnswer()
        for (response in responses) {
            val rb = RadioButton(context)
            rb.text = response
            radio_group.addView(rb)
            if (response == answer) {
                rb.isChecked = true
                rb.jumpDrawablesToCurrentState()
            }
        }
    }

    fun onRadioButtonSelect(response: String) {
        next_button.isEnabled = true
        viewModel.setAnswer(response)
    }
}