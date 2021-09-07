package com.demal.feature_home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.demal.feature_home.R
import com.demal.feature_home.databinding.FragmentHomeBinding
import com.demal.feature_home.main.adapter.homeTourBind
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, LikableTours, HomeViewModel>() {

    override var bindingNullable: FragmentHomeBinding? = null

    override val viewModel: HomeViewModel by viewModel()

    private var tourAdapter: BaseAdapter<LikableTour, TourClickListener>? = null

    private val imageLoader: ImageLoader<ImageView> by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun initAdapter() {
        if (tourAdapter == null) {
            tourAdapter = BaseAdapter(R.layout.home_item_tour, tourClickListener) { view, data, listener ->
                homeTourBind(view, data, listener, imageLoader)
            }

            binding.popularToursRecyclerview.adapter = tourAdapter
            binding.recommendationsRecyclerview.adapter = tourAdapter
        }
    }

    private val tourClickListener = object : TourClickListener {
        override fun onLikeClick(tour: LikableTour) {
            viewModel.likePressed(tour)
        }

        override fun onItemClick(tour: LikableTour) {
            viewModel.openTour(tour)
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