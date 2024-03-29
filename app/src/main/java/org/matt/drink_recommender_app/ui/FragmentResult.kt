package org.matt.drink_recommender_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_result.*
import org.matt.drink_recommender_app.R
import org.matt.drink_recommender_app.viewmodel.AppViewModel

class FragmentResult : Fragment(){

    lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[AppViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.getDrinkRecommendation()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recommendedDrinkLiveData.observe(viewLifecycleOwner, Observer {
            displayDrinkRecommendation.text = getString(R.string.drinkRecommendation, it)
            displayDrinkImage(it)
        })

        agree_button.setOnClickListener({agreeButtonClick()})
        disagree_button.setOnClickListener({disagreeButtonClick()})
    }

    fun displayDrinkImage(drinkName: String) {
        val imageResource: Int =
        when(drinkName) {
            "beer" -> R.drawable.beer
            "wine" -> R.drawable.wine
            "water" -> R.drawable.water
            "soda" -> R.drawable.soda
            else -> -1
        }
        drink_image.setImageResource(imageResource)
    }

    fun agreeButtonClick() {
        if (viewModel.recommendedDrinkLiveData.value != null) {
            viewModel.sendResponse(viewModel.recommendedDrinkLiveData.value.toString())
        }

        // TODO: display finish dialog
    }

    fun disagreeButtonClick() {
        findNavController().navigate(R.id.action_fragmentResult_to_fragmentDrinkMenuLayout)
    }

}