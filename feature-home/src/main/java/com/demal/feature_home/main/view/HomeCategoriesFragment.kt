package com.demal.feature_home.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demal.feature_home.R
import com.demal.feature_home.databinding.FragmentHomeCategoriesBinding
import com.demal.feature_home.main.adapter.CategoryClickListener
import com.demal.feature_home.main.adapter.homeCategoryBind
import com.demal.feature_home.main.view_model.HomeCategoriesViewModel
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.category.CategoriesResponse
import com.demal.model.data.entity.category.CategoryResponse
import com.demal.view.core.adapter.BaseAdapter
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeCategoriesFragment :
    BaseFragment<FragmentHomeCategoriesBinding, CategoriesResponse, HomeCategoriesViewModel>() {

    override var bindingNullable: FragmentHomeCategoriesBinding? = null

    override val viewModel: HomeCategoriesViewModel by viewModel()

    private var categoryAdapter: BaseAdapter<CategoryResponse, CategoryClickListener>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeCategoriesBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoryAdapter()
        viewModel.getCategories()
    }

    override fun renderData(state: BaseState<CategoriesResponse>) {
        hideLoading()
        super.renderData(state)
    }

    override fun renderSuccess(data: CategoriesResponse) {
        showCategoriesData(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
        categoryAdapter = null
    }

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun initCategoryAdapter() {
        if (categoryAdapter == null) {
            categoryAdapter = BaseAdapter(
                R.layout.home_item_category,
                categoryClickListener
            ) { view, data, listener ->
                homeCategoryBind(view, data, listener)
            }

            binding.activitiesRecyclerview.adapter = categoryAdapter
        }
    }

    private val categoryClickListener = object : CategoryClickListener {
        override fun onItemClick(category: CategoryResponse) {
            viewModel.openToursByCategory(category)
        }
    }

    private fun showCategoriesData(data: CategoriesResponse) {
        if (data.categories.isEmpty()) {
            showWarningCategoryMsg()
        } else {
            showCategoriesList(data.categories)
        }
    }

    private fun showCategoriesList(data: List<CategoryResponse>) {
        binding.activitiesInfoTextview.visibility = View.GONE
        categoryAdapter?.submitList(data)
    }

    private fun showWarningCategoryMsg() {
        binding.activitiesInfoTextview.visibility = View.VISIBLE
        categoryAdapter?.submitList(listOf())
    }

    private fun showLoading() {
        binding.categoriesProgressBar.show()
    }

    private fun hideLoading() {
        if (binding.categoriesProgressBar.isShown) {
            binding.categoriesProgressBar.hide()
        }
    }
}
