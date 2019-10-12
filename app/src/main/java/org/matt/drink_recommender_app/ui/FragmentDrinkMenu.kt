package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_drink_menu.*
import org.matt.drink_recommender_app.R
import org.matt.drink_recommender_app.viewmodel.AppViewModel

class FragmentDrinkMenu : Fragment() {
    lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drink_menu, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[AppViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choiceBeer.setOnClickListener({viewModel.sendResponse(choiceBeer.contentDescription as String)})
        choiceWine.setOnClickListener({viewModel.sendResponse(choiceWine.contentDescription as String)})
        choiceWater.setOnClickListener({viewModel.sendResponse(choiceWater.contentDescription as String)})
        choiceSoda.setOnClickListener({viewModel.sendResponse(choiceSoda.contentDescription as String)})
    }

}