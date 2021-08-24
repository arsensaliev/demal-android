package com.demal.feature_profile_edit.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demal.feature_profile_edit.R
import com.demal.feature_profile_edit.databinding.FragmentProfileEditBinding
import com.demal.view.core.view.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.demal.model.data.entity.user.User
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileEditFragment :
    BaseFragment<FragmentProfileEditBinding, User, ProfileEditViewModel>() {

    lateinit var validatorFirstName: EditTextValidator
    lateinit var validatorLastName: EditTextValidator
    lateinit var validatorCountry: EditTextValidator
    lateinit var validatorCity: EditTextValidator

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
        initEditTextValidator()
        setButtonSaveListener()
    }

    private fun initEditTextValidator() {

        validatorFirstName = EditTextValidator(
            binding.inputLayoutFirstName,
            binding.inputEditTextFirstName,
            resources.getString(R.string.profile_edit_first_name_err)
        )

        validatorLastName = EditTextValidator(
            binding.inputLayoutLastName,
            binding.inputEditTextLastname,
            resources.getString(R.string.profile_edit_last_name_err)
        )

        validatorCountry = EditTextValidator(
            binding.inputLayoutCountry,
            bindingNullable?.inputEditTextCountry,
            resources.getString(R.string.profile_edit_сountry_err)
        )

        validatorCity = EditTextValidator(
            binding.inputLayoutCity,
            binding.inputEditTextCity,
            resources.getString(R.string.profile_edit_сity_err)
        )

    }

    private fun setButtonSaveListener() {
        binding.buttonSend.setOnClickListener {
            checkForErrorsAll()
        }
    }

    fun checkForErrorsAll() {
        validatorFirstName.checkForError()
        validatorLastName.checkForError()
        validatorCountry.checkForError()
        validatorCity.checkForError()
    }


}




