package com.demal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demal.feature_mytours.R
import com.demal.feature_mytours.databinding.FragmentMyToursBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.view.core.adapter.BaseAdapter

import com.demal.view.core.adapter.listeners.MyTourClickListener
import com.demal.view.core.view.BaseFragment
import com.demal.view_model.MyToursViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MyToursFragment : BaseFragment<FragmentMyToursBinding, LikableTours, MyToursViewModel>(),
    ListenerState {

    override var bindingNullable: FragmentMyToursBinding? = null
    private var adapter: BaseAdapter<LikableTour, MyTourClickListener>? = null

    override val viewModel: MyToursViewModel by viewModel()

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
//        adapter = BaseAdapter(R.layout.item_tour,clickListener,  )
        viewModel.getTours()
        binding.activeInactiveButton.listenerState = this
        binding.rvFragmentMyTours.adapter = adapter
    }

    override fun onStateChanged(state: Boolean) {
        viewModel.getTours(state)
    }

    override fun setLoading(isLoading: Boolean){
        if (isLoading) {
            showProgressBar()
        }
    }

    override fun renderSuccess(data: LikableTours) {
        showList()
        if (data.toursList.isEmpty()){
            showTextIsEmpty()
        } else{
            adapter?.submitList(data.toursList)
        }
    }

    private val clickListener = object : MyTourClickListener {
        override fun onLikeClick(tour: LikableTour) {
            viewModel.onLikeClick(tour)
        }

        override fun onItemClick(tour: LikableTour) {
            viewModel.onItemClick(tour)
        }
    }

    private fun showProgressBar() {
        binding.rvFragmentMyTours.visibility = View.INVISIBLE
        binding.progressIndicatorFragmentMyTours.visibility = View.VISIBLE
    }

    private fun showList() {
        binding.rvFragmentMyTours.visibility = View.VISIBLE
        binding.progressIndicatorFragmentMyTours.visibility = View.GONE
    }
    private fun showTextIsEmpty(){
        binding.tvIfEmptyFragmentMyTours.visibility = View.VISIBLE
    }
}