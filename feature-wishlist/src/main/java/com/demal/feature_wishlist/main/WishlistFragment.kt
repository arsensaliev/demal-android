package com.demal.feature_wishlist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.demal.feature_wishlist.R
import com.demal.feature_wishlist.databinding.FragmentWishlistBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.adapter.tourBind
import com.demal.view.core.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class WishlistFragment : BaseFragment<FragmentWishlistBinding, LikableTours, WishlistViewModel>() {
    override var bindingNullable: FragmentWishlistBinding? = null

    override val viewModel: WishlistViewModel by viewModel()

    private var tourAdapter: BaseAdapter<LikableTour, TourClickListener>? = null

    private val imageLoader: ImageLoader<ImageView> by inject()

    private val tourClickListener = object : TourClickListener {
        override fun onLikeClick(tour: LikableTour) {
            viewModel.likePressed(tour)
        }

        override fun onItemClick(tour: LikableTour) {
            viewModel.openTour(tour)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWishlistBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        bindingNullable = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        viewModel.getLikedTours()
    }

    override fun renderSuccess(data: LikableTours) {
        if (data.toursList.isNullOrEmpty()) {
            listIsEmpty()
        } else {
            showList(data.toursList)
        }
    }

    override fun renderData(state: BaseState<LikableTours>) {
        hideLoading()
        super.renderData(state)
    }

    override fun setLoading(isLoading: Boolean) {
        showLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
        tourAdapter = null
    }

    private fun initRV() {
        tourAdapter ?: let {
            tourAdapter =
                BaseAdapter(R.layout.item_tour, tourClickListener) { view, data, listener ->
                    tourBind(view, data, listener, imageLoader)
                }
            binding.wishlistRecyclerView.adapter = tourAdapter
            binding.wishlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun listIsEmpty() {
        binding.wishlistEmptyTextview.visibility = View.VISIBLE
        tourAdapter?.submitList(listOf())
    }

    private fun showList(data: List<LikableTour>) {
        binding.wishlistEmptyTextview.visibility = View.GONE
        tourAdapter?.submitList(data)
    }

    private fun showLoading() {
        binding.wishlistProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.wishlistProgressBar.isShown) {
            binding.wishlistProgressBar.hide()
        }
    }
}