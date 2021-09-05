package com.demal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.demal.databinding.FragmentTourBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.model.data.entity.tours.network.ImageResponse
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.adapter.tourBind
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class TourFragment :
    BaseFragment<FragmentTourBinding, LikableTour, TourViewModel>() {

    override var bindingNullable: FragmentTourBinding? = null

    override val viewModel: TourViewModel by viewModel()

    private var imageAdapter: BaseAdapter<LikableTour, TourClickListener>? = null

    private val imageLoader: ImageLoader<ImageView> by inject()

    private val tourClickListener = object : TourClickListener {
        override fun onLikeClick(tour: LikableTour) {
            viewModel.likePressed(tour)
        }

        override fun onItemClick(tour: LikableTour) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTourBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVP()
        viewModel.getTourDescription(tourId) //error, help
        binding.closeButton.setOnClickListener {
            viewModel.closeTour()
        }
    }

    private fun initVP() {
        imageAdapter ?: let {
            imageAdapter =
                BaseAdapter(R.layout.item_view_pager, tourClickListener) { view, data, listener ->
                    tourBind(view, data, listener, imageLoader)
                }
            binding.viewPager.adapter = imageAdapter
            binding.indicator.setViewPager(binding.viewPager)
        }
    }

    override fun renderSuccess(data: LikableTour) {
        if (data.images.isNullOrEmpty()){
            tourIsEmpty()
        }else {
            showImages(data.images) //error, help
        }
    }

    override fun renderData(state: BaseState<LikableTour>) {
        hideLoading()
        super.renderData(state)
    }

    override fun setLoading(isLoading: Boolean) {
        showLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
        imageAdapter = null
    }

    private fun tourIsEmpty(){
        imageAdapter?.submitList(listOf())
    }

    private fun showImages(data: List<LikableTour>){
        imageAdapter?.submitList(data)
    }

    private fun showLoading() {
        binding.tourImageProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.tourImageProgressBar.isShown) {
            binding.tourImageProgressBar.hide()
        }
    }
}