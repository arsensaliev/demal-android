package com.demal.feature_profile.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.demal.constants.BASE_SPACES_URL
import com.demal.feature_profile.R
import com.demal.feature_profile.databinding.FragmentProfileBinding
import com.demal.model.data.entity.user.User
import com.demal.repository.image.ImageLoader
import com.demal.view.core.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, User, ProfileViewModel>() {

    private val imageLoader: ImageLoader<ImageView> by inject()

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
        setButtonEditListener()
    }

    override fun renderSuccess(data: User) {
        imageLoader.loadImage(1, binding.avatarImage, "$BASE_SPACES_URL/${data.imagePath}")
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
    fun setButtonEditListener(){
        binding.buttonEdit.setOnClickListener {
            viewModel.navigator.toProfileEditScreen()
        }
    }
}