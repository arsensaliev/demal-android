package com.demal.feature_profile.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        //Location пока что не приходит с сервера
        binding.profileLocation.text = data.email
    }
}