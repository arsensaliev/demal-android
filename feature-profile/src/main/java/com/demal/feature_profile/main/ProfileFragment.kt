package com.demal.feature_profile.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demal.feature_profile.R
import com.demal.feature_profile.databinding.FragmentProfileBinding
import com.demal.model.data.entity.user.User
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, User, ProfileViewModel>() {

    override var bindingNullable: FragmentProfileBinding? = null

    override val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        initListeners()
    }

    private fun initListeners() {
        binding.buttonEdit.setOnClickListener {
            viewModel.navigateToProfileEditScreen()
        }
    }

    override fun renderSuccess(data: User) {
        with(data) {
            binding.profileName.text = firstName

            val locationStr =
                if (city == null && country != null) country
                else if (country == null && city != null) city
                else if (city != null && country != null) getString(
                    R.string.location_display,
                    country,
                    city
                ) else ""

            binding.profileLocation.text = locationStr
        }

    }
}