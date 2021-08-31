package com.demal.feature_home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demal.feature_home.R
import com.demal.feature_home.databinding.FragmentHomeBinding
import com.demal.feature_home.main.adapter.homeTourBind
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, LikableTours, HomeViewModel>() {

    override var bindingNullable: FragmentHomeBinding? = null

    override val viewModel: HomeViewModel by viewModel()

    private var tourAdapter: BaseAdapter<LikableTour, TourClickListener>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onStart() {
        super.onStart()
        initAdapter()
        viewModel.getTours()
    }

    override fun renderData(state: BaseState<LikableTours>) {
        hideLoading()
        super.renderData(state)
    }

    override fun renderSuccess(data: LikableTours) {
        if (data.toursList.isEmpty()) {
            showWarningMsg()
        } else {
            showToursList(data.toursList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
        tourAdapter = null
    }

    override fun showLoading(isLoading: Boolean) {
        showLoading()
    }

    private fun initAdapter() {
        if (tourAdapter == null) {
            tourAdapter = BaseAdapter(R.layout.home_item_tour, homeTourBind, clickListener)

            binding.popularToursRecyclerview.adapter = tourAdapter
            binding.recommendationsRecyclerview.adapter = tourAdapter
        }
    }

    private val clickListener = object : TourClickListener {
        override fun onLikeClick(id: Int) {
            viewModel.likePressed(id)
        }

        override fun onItemClick(tour: LikableTour) {
        }
    }

    private fun showToursList(data: List<LikableTour>) {
        binding.popularToursInfoTextview.visibility = View.GONE
        binding.recommendationsInfoTextview.visibility = View.GONE
        binding.activitiesInfoTextview.visibility = View.GONE
        tourAdapter?.submitList(data)
    }

    private fun showWarningMsg() {
        binding.popularToursInfoTextview.visibility = View.VISIBLE
        binding.recommendationsInfoTextview.visibility = View.VISIBLE
        binding.activitiesInfoTextview.visibility = View.VISIBLE
        tourAdapter?.submitList(listOf())
    }

    private fun showLoading() {
        binding.popularToursProgressBar.show()
        binding.recommendationsProgressBar.show()
        binding.categoriesProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.popularToursProgressBar.isShown) {
            binding.popularToursProgressBar.hide()
        }
        if (binding.recommendationsProgressBar.isShown) {
            binding.recommendationsProgressBar.hide()
        }
        if (binding.categoriesProgressBar.isShown) {
            binding.categoriesProgressBar.hide()
        }
    }
}