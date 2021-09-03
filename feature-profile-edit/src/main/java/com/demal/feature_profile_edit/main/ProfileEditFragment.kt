package com.demal.feature_profile_edit.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.demal.feature_profile_edit.R
import com.demal.feature_profile_edit.databinding.FragmentProfileEditBinding
import com.demal.model.data.entity.user.User
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileEditFragment :
    BaseFragment<FragmentProfileEditBinding, User, ProfileEditViewModel>() {

    private var validator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
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


    override fun renderSuccess(data: User) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditTextValidator()
        setButtonSaveListener()
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

    fun checkForErrorsAll() {
        validator.validate()
    }

}
