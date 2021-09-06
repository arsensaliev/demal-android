package com.demal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.demal.databinding.FragmentTourBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.ImageLoader
import com.demal.view.core.view.BaseFragment
import me.relex.circleindicator.CircleIndicator3
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TourFragment(private val tourId: Int) :
    BaseFragment<FragmentTourBinding, LikableTour, TourViewModel>() {

    override var bindingNullable: FragmentTourBinding? = null

    override val viewModel: TourViewModel by viewModel()

    private var adapter: TourAdapter? = null

    private lateinit var viewPager: ViewPager2

    private lateinit var indicator: CircleIndicator3

    private val imageLoader: ImageLoader<ImageView> by inject()

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

        val images = viewModel.getTourImages(tourId)
        viewModel.getTourDescription(tourId)

        viewPager = binding.viewPager
        adapter = TourAdapter(images)
        indicator = binding.indicator

        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)

        binding.closeButton.setOnClickListener {
            viewModel.closeTour()
        }
    }

    override fun renderSuccess(data: LikableTour) {
        if (data.description.isNullOrEmpty()){
            tourIsEmpty()
        }else {
            initTour(data)
        }
    }

    private fun initTour(data: LikableTour) {
        with(binding.tourBottomSheet){
            textViewPlace.text = data.place
            textViewDescription.text = data.description
            textViewPeople.text= data.travelersCount.toString()
            textViewDate.text = data.startDate
        }
    }

    private fun tourIsEmpty() {
        binding.tourBottomSheet.textViewDescription.text = "Что-то пошло не так"
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
        adapter = null
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