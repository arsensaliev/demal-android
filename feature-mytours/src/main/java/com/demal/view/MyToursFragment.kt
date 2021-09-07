package com.demal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.demal.feature_mytours.R
import com.demal.feature_mytours.databinding.FragmentMyToursBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.adapter.tourBind
import com.demal.view.core.view.BaseFragment
import com.demal.view_model.MyToursViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MyToursFragment : BaseFragment<FragmentMyToursBinding, LikableTours, MyToursViewModel>(),
    ListenerState {

    override var bindingNullable: FragmentMyToursBinding? = null
    private var adapter: BaseAdapter<LikableTour, TourClickListener>? = null

    override val viewModel: MyToursViewModel by viewModel()
    private val imageLoader: ImageLoader<ImageView> by inject()
    private var stateView = false

    private val clickListener = object : TourClickListener {
        override fun onLikeClick(tour: LikableTour) {
            viewModel.onLikeClick(tour, stateView)
        }

        override fun onItemClick(tour: LikableTour) {
            viewModel.onItemClick(tour.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentMyToursBinding.inflate(inflater, container, false)
            .apply { bindingNullable = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        adapter ?: let {
            adapter =
                BaseAdapter(R.layout.item_tour, clickListener) { view, data, listener ->
                    tourBind(view, data, listener, imageLoader)
                }
            binding.rvFragmentMyTours.adapter = adapter
        }

        viewModel.getInactiveTour()
        binding.activeInactiveButton.listenerState = this
    }

    override fun onStateChanged(state: Boolean) {
        stateView = state
        if (state){
            viewModel.getActiveTour()
        } else{
            viewModel.getInactiveTour()
        }
    }

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showProgressBar()
        }
    }

    override fun renderSuccess(data: LikableTours) {
        showList()
        if (data.toursList.isEmpty()) {
            showTextIsEmpty()
        } else {
            adapter?.submitList(data.toursList)
        }
    }

    private fun showProgressBar() {
        binding.tvIfEmptyFragmentMyTours.visibility = View.GONE
        binding.rvFragmentMyTours.visibility = View.INVISIBLE
        binding.progressIndicatorFragmentMyTours.visibility = View.VISIBLE
    }

    private fun showList() {
        binding.rvFragmentMyTours.visibility = View.VISIBLE
        binding.tvIfEmptyFragmentMyTours.visibility = View.GONE
        binding.progressIndicatorFragmentMyTours.visibility = View.GONE
    }

    private fun showTextIsEmpty() {
        binding.rvFragmentMyTours.visibility = View.INVISIBLE
        binding.tvIfEmptyFragmentMyTours.visibility = View.VISIBLE
    }
}