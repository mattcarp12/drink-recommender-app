package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_q1.*
import kotlinx.android.synthetic.main.fragment_start.*
import org.matt.drink_recommender_app.R

class Q1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_q1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, _ ->
            q1_next_button.setEnabled(true)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Q1()
    }

}
