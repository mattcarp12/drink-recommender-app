package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_start.*
import org.matt.drink_recommender_app.R


class FragmentStart : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_button.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_fragmentStart_to_fragmentQuestion,
                null
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentStart()
    }
}