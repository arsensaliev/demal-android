package com.demal.feature_profile_edit.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demal.feature_profile_edit.R
import com.demal.feature_profile_edit.databinding.FragmentProfileEditBinding
import com.demal.view.core.view.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.demal.model.data.entity.user.User
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileEditFragment :
    BaseFragment<FragmentProfileEditBinding, User, ProfileEditViewModel>() {

    override var bindingNullable: FragmentProfileEditBinding? = null

    override val viewModel: ProfileEditViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileEditBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onStart() {
        super.onStart()
        viewModel.init()
    }

    override fun renderSuccess(data: User) {
    }
}




