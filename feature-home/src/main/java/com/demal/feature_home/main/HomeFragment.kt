package com.demal.feature_home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demal.feature_home.databinding.FragmentHomeBinding
import com.demal.model.data.entity.tours.Tours
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, Tours, HomeViewModel>() {

    override var bindingNullable: FragmentHomeBinding? = null

    override val viewModel : HomeViewModel by viewModel()

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
        viewModel.init()
    }

    override fun renderSuccess(data: Tours) {

    }
}