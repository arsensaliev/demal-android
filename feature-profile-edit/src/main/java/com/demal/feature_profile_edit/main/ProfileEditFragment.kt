package com.demal.feature_profile_edit.main

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.demal.constants.BASE_SPACES_URL
import com.demal.feature_profile_edit.R
import com.demal.feature_profile_edit.databinding.FragmentProfileEditBinding
import com.demal.model.data.entity.user.User
import com.demal.repository.image.ImageLoader
import com.demal.view.core.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileEditFragment :
    BaseFragment<FragmentProfileEditBinding, User, ProfileEditViewModel>() {

    private val imageLoader: ImageLoader<ImageView> by inject()
    private lateinit var validator: AwesomeValidation
    override var bindingNullable: FragmentProfileEditBinding? = null
    override val viewModel: ProfileEditViewModel by viewModel()

    private val contentPhoto = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.also {
            updatePhoto(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileEditBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun renderSuccess(user: User) {
        imageLoader.loadImage(1, binding.imgAvatar, "$BASE_SPACES_URL/${user.imagePath}")

        binding.inputEditTextFirstName.setText(user.firstName)
        binding.inputEditTextLastname.setText(user.lastName)
        binding.inputEditTextCountry.setText(user.country)
        binding.inputEditTextCity.setText(user.city)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        initEditTextValidator()
        setButtonSaveListener()
        setSelectPhotoListener()
    }

    private fun initEditTextValidator() {

        validator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        validator.setTextInputLayoutErrorTextAppearance(R.style.errorTextInputLayout)

        validator.addValidation(
            binding.inputLayoutFirstName,
            RegexTemplate.NOT_EMPTY,
            getString(R.string.profile_edit_first_name_err)
        )

        validator.addValidation(
            binding.inputLayoutLastName,
            RegexTemplate.NOT_EMPTY,
            resources.getString(R.string.profile_edit_last_name_err)
        )

        validator.addValidation(
            binding.inputLayoutCountry,
            RegexTemplate.NOT_EMPTY,
            resources.getString(R.string.profile_edit_сountry_err)
        )

        validator.addValidation(
            binding.inputLayoutCity,
            RegexTemplate.NOT_EMPTY,
            resources.getString(R.string.profile_edit_сity_err)
        )

    }

    private fun setButtonSaveListener() {
        binding.buttonSend.setOnClickListener {
            checkForErrorsAll()
        }
    }

    private fun setSelectPhotoListener() {
        binding.photo.setOnClickListener {
            contentPhoto.launch("image/*")
        }
    }

    fun checkForErrorsAll() {
        validator.validate()
    }

    fun updatePhoto(uriPhoto: Uri) {
        binding.imgAvatar.setImageURI(uriPhoto)
    }

}
