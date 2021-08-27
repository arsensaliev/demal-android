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

    private var mAwesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditTextValidator()
        setButtonSaveListener()
    }

    private fun initEditTextValidator() {

        mAwesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        mAwesomeValidation.setTextInputLayoutErrorTextAppearance(R.style.errorTextInputLayout);

        mAwesomeValidation.addValidation(
            binding.inputLayoutFirstName,
            RegexTemplate.NOT_EMPTY,
            getString(R.string.profile_edit_first_name_err)
        )

        mAwesomeValidation.addValidation(
            binding.inputLayoutLastName,
            RegexTemplate.NOT_EMPTY,
            resources.getString(R.string.profile_edit_last_name_err)
        )

        mAwesomeValidation.addValidation(
            binding.inputLayoutCountry,
            RegexTemplate.NOT_EMPTY,
            resources.getString(R.string.profile_edit_сountry_err)
        )

        mAwesomeValidation.addValidation(
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
        mAwesomeValidation.validate()
    }

}




