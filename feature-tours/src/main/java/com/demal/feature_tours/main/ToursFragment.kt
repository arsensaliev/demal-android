package com.demal.feature_tours.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.demal.feature_tours.R
import com.demal.feature_tours.databinding.FragmentToursBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.category.Category
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.ToursState
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.adapter.listeners.TourClickListener
import com.demal.view.core.adapter.tourBind
import com.demal.view.core.view.BaseFragment
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ToursFragment : BaseFragment<FragmentToursBinding, ToursState, ToursViewModel>() {

    override var bindingNullable: FragmentToursBinding? = null

    override val viewModel: ToursViewModel by viewModel()

    private var tourAdapter: BaseAdapter<LikableTour, TourClickListener>? = null

    private val imageLoader: ImageLoader<ImageView> by inject()

    private var inputCategoryId: Int? = null

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
    ): View = FragmentToursBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        bindingNullable = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputCategoryId = arguments?.getInt(CATEGORY_ID_EXTRA)
        initRV()
        viewModel.getTours()
        inputCategoryId?.let {
            viewModel.setupFilter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
        tourAdapter = null
    }

    override fun renderData(state: BaseState<ToursState>) {
        hideLoading()
        super.renderData(state)
    }

    override fun renderSuccess(data: ToursState) {
        fillCategories(data.toursCategories)
        if (data.toursList.isNullOrEmpty()) {
            listIsEmpty()
        } else {
            showList(data.toursList)
        }
    }

    override fun setLoading(isLoading: Boolean) {
        showLoading()
    }

    private fun initRV() {
        tourAdapter ?: let {
            tourAdapter =
                BaseAdapter(R.layout.item_tour, tourClickListener) { view, data, listener ->
                    tourBind(view, data, listener, imageLoader)
                }
            binding.toursRecyclerView.adapter = tourAdapter
            binding.toursRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun fillCategories(categories: List<Category>) {
        val chipGroup = binding.chipCategoriesGroup
        binding.chipCategoryDefault.setOnClickListener {
            viewModel.setupFilter(null)
        }
        categories.forEach { category ->
            val chip = layoutInflater.inflate(R.layout.single_chip_layout, chipGroup, false) as Chip
            chip.text = category.name
            chip.isCheckable = true
            if (category.id == inputCategoryId) {
                chip.isChecked = true
                binding.toursChipsHorizontalScroll.postDelayed({
                    binding.toursChipsHorizontalScroll.smoothScrollTo(chip.x.toInt(), 0)
                }, 100)
            }
            chip.setOnClickListener {
                viewModel.setupFilter(category.id)
            }
            chipGroup.addView(chip)
        }
    }

    private fun listIsEmpty() {
        binding.toursEmptyTextview.visibility = View.VISIBLE
        tourAdapter?.submitList(listOf())
    }

    private fun showList(data: List<LikableTour>) {
        binding.toursEmptyTextview.visibility = View.GONE
        tourAdapter?.submitList(data)
    }

    private fun showLoading() {
        binding.toursProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.toursProgressBar.isShown) {
            binding.toursProgressBar.hide()
        }
    }

    companion object {
        private const val CATEGORY_ID_EXTRA = "com.demal.feature_tours.main.CATEGORY_ID_EXTRA"

        fun newInstance(categoryId: Int?): ToursFragment {
            return ToursFragment().apply {
                categoryId?.let {
                    arguments = bundleOf(
                        CATEGORY_ID_EXTRA to categoryId
                    )
                }
            }
        }
    }
}