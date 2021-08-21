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
        setEditorListener()
        setButtonSaveListener()
    }

    private fun setEditorListener() {
        bindingNullable?.inputEditTextFirstName?.setOnEditorActionListener { _, _, _ ->
            this.checkForErrorFirstName()
        }
        bindingNullable?.inputEditTextLastname?.setOnEditorActionListener { _, _, _ ->
            this.checkForErrorLastName()
        }
        bindingNullable?.inputEditTextCountry?.setOnEditorActionListener { _, _, _ ->
            this.checkForErrorCountry()
        }
        bindingNullable?.inputEditTextCity?.setOnEditorActionListener { _, _, _ ->
            this.checkForErrorCity()
        }
    }

    private fun setButtonSaveListener() {
        bindingNullable?.buttonSend?.setOnClickListener {
            checkForErrorsAll()
        }
    }

    fun checkForErrorsAll() {
        checkForErrorFirstName()
        checkForErrorLastName()
        checkForErrorCountry()
        checkForErrorCity()
    }

    private fun checkForErrorFirstName(): Boolean {
        return errorMessage(
            bindingNullable?.inputLayoutFirstName,
            bindingNullable?.inputEditTextFirstName,
            resources.getString(R.string.profile_edit_first_name_err)
        )
    }

    private fun checkForErrorLastName(): Boolean {
        return errorMessage(
            bindingNullable?.inputLayoutLastName,
            bindingNullable?.inputEditTextLastname,
            resources.getString(R.string.profile_edit_last_name_err)
        )
    }

    fun checkForErrorCountry(): Boolean {
        return errorMessage(
            bindingNullable?.inputLayoutCountry,
            bindingNullable?.inputEditTextCountry,
            resources.getString(R.string.profile_edit_сountry_err)
        )
    }

    fun checkForErrorCity(): Boolean {
        return errorMessage(
            bindingNullable?.inputLayoutCity,
            bindingNullable?.inputEditTextCity,
            resources.getString(R.string.profile_edit_сity_err)
        )
    }

    private fun errorMessage(
        inputLayout: TextInputLayout?,
        inputEditText: TextInputEditText?,
        textMessageError: String
    ): Boolean {
        if (inputLayout != null && inputEditText != null) {

            return if (shouldShowError(inputEditText)) {
                showErrorOff(inputLayout)
                false
            } else {
                showErrorOn(inputLayout, textMessageError)
                true
            }
        }
        return false
    }

    private fun showErrorOn(inputLayout: TextInputLayout, textMessageError: String) {
        inputLayout.error = textMessageError
    }

    private fun showErrorOff(inputLayout: TextInputLayout) {
        inputLayout.setError(null)
    }

    private fun shouldShowError(inputEditText: TextInputEditText): Boolean {
        if (inputEditText.text != null) {
            val textLength = inputEditText.text!!.length
            return textLength > 0
        }
        return false
    }
}




