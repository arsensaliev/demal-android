package com.demal.feature_tour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.demal.feature_tour.R
import com.demal.feature_tour.databinding.FragmentTourBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.network.ImageResponse
import com.demal.repository.image.ImageLoader
import com.demal.utils.date.DateIdentifier
import com.demal.view.core.view.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TourFragment :
    BaseFragment<FragmentTourBinding, LikableTour, TourViewModel>() {

    override var bindingNullable: FragmentTourBinding? = null
    override val viewModel: TourViewModel by viewModel()
    private val imageLoader: ImageLoader<ImageView> by inject()

    private var tourId: Int = -1

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

        tourId =
            arguments?.getInt(TOUR_ID_EXTRA) ?: throw IllegalArgumentException("No tour id income")
        viewModel.stateLiveData.observe(viewLifecycleOwner) { tour ->
            renderData(tour)
        }
        viewModel.getTour(tourId)
        binding.tourFragmentCloseButton.setOnClickListener {
            viewModel.closeTour()
        }
        binding.tourBottomSheet.imageViewLike.setOnClickListener {
            viewModel.likePressed()
        }
    }

    override fun renderSuccess(data: LikableTour) {
        if (data.description.isNullOrEmpty()) {
            tourIsEmpty()
        } else {
            initTour(data)
            initViewPager(data.images)
        }
    }

    private fun initViewPager(images: List<ImageResponse>) {
        with(binding) {
            viewPager.adapter = TourAdapter(imageLoader, images)
            indicator.setViewPager(viewPager)
        }
    }

    private fun initTour(data: LikableTour) {
        with(binding.tourBottomSheet) {
            val dif = DateIdentifier()
            val date = dif.mapDateCustomFormat(data.startDate)
            textViewDate.text = date
            textViewPlace.text = data.place
            textViewDescription.text = data.description
            textViewPeople.text = data.travelersCount.toString()
            val likeButtonImage = if (data.isLiked) {
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_button_like_filled)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_button_like_empty)
            }
            imageViewLike.setImageDrawable(likeButtonImage)
            val bottomSheetBehavior = BottomSheetBehavior.from(tourBottomSheetLayout)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
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
    }

    private fun showLoading() {
        binding.tourImageProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.tourImageProgressBar.isShown) {
            binding.tourImageProgressBar.hide()
        }
    }

    companion object {
        private const val TOUR_ID_EXTRA = "com.demal.feature_tour.main.TOUR_ID_EXTRA"

        fun newInstance(tourId: Int): TourFragment {
            return TourFragment().apply {
                arguments = bundleOf(
                    TOUR_ID_EXTRA to tourId
                )
            }
        }
    }
}