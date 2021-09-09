package com.demal.feature_login.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.demal.feature_login.R
import com.demal.feature_login.databinding.FragmentLoginBinding
import com.demal.model.data.entity.login.LoginStatus
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginStatus, LoginViewModel>() {

    override var bindingNullable: FragmentLoginBinding? = null
    override val viewModel: LoginViewModel by viewModel()
    private val emailValidator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
    private val passwordValidator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        initEmailValidation()
        initPasswordValidation()
        checkValidation()
    }

    private fun setupListeners() = with(binding) {
        editTextPassword.addTextChangedListener(TextFieldValidation(editTextPassword))
        editTextEmail.addTextChangedListener(TextFieldValidation(editTextEmail))
    }

    private fun initEmailValidation() {
        emailValidator.setTextInputLayoutErrorTextAppearance(R.style.ErrorTextAppearance)
        emailValidator.addValidation(
            binding.textInputLayoutEmail,
            android.util.Patterns.EMAIL_ADDRESS,
            getString(R.string.wrong_email)
        )
    }

    private fun initPasswordValidation() {
        passwordValidator.setTextInputLayoutErrorTextAppearance(R.style.ErrorTextAppearance)
        passwordValidator.addValidation(
            binding.textInputLayoutPassword,
            getString(R.string.regex_password_validation),
            getString(R.string.password_rule)
        )
    }

    private fun checkValidation() = with(binding) {
        buttonLogin.setOnClickListener {
            if (emailValidator.validate() && passwordValidator.validate()) {
                viewModel.login(
                    binding.editTextEmail.text.toString().trim(),
                    binding.editTextPassword.text.toString().trim()
                )
            } else {
                textInputLayoutPassword.error = getString(R.string.password_rule)
            }
        }
    }

    override fun renderError(error: Throwable) {
        renderMessage(error.message.toString())
    }

    override fun renderSuccess(data: LoginStatus) {
        viewModel.navigator.toProfileScreen()
    }


    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // not used
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // not used
        }

        override fun afterTextChanged(s: Editable?) {
            when (view.id) {
                R.id.edit_text_email -> emailValidator.validate()
                R.id.edit_text_password -> passwordValidator.validate()
            }
        }
    }
}