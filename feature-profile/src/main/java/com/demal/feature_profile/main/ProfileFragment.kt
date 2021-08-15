package com.demal.feature_profile.main

import android.os.Bundle
import android.view.LayoutInflater
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

    override fun onStart() {
        super.onStart()
        viewModel.init()
    }

    override fun renderSuccess(data: User) {
        binding.profileName.text = data.firstName

        with(data) {
            binding.profileLocation.text = getString(
                R.string.location_display,
                country,
                city
            )
        }

    }
}