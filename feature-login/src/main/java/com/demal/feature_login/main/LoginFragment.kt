package com.demal.feature_login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.demal.feature_login.R
import com.demal.feature_login.databinding.FragmentLoginBinding
import com.demal.model.data.entity.login.LoginStatus
import com.demal.model.data.exceptions.BadRequestException
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
        init()
    }

    private fun init() {
        setUpValidation()
        initEditTextValidation()
        checkValidation()
    }

    private fun setUpValidation() = with(binding) {
        passwordValidator.setTextInputLayoutErrorTextAppearance(R.style.ErrorTextAppearance)
        emailValidator.setTextInputLayoutErrorTextAppearance(R.style.ErrorTextAppearance)
        AwesomeValidation.disableAutoFocusOnFirstFailure()

        editTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailValidator.clear()
            } else {
                emailValidator.validate()
            }
        }

        editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordValidator.clear()
            } else {
                passwordValidator.validate()
            }
        }
    }

    private fun initEditTextValidation() = with(binding) {
        emailValidator.addValidation(
            textInputLayoutEmail,
            android.util.Patterns.EMAIL_ADDRESS,
            getString(R.string.wrong_email)
        )

        passwordValidator.addValidation(
            binding.textInputLayoutPassword,
            getString(R.string.regex_password_validation),
            getString(R.string.password_rule)
        )
    }

    private fun checkValidation() = with(binding) {
        buttonLogin.setOnClickListener {
            val email =
                if (emailValidator.validate()) editTextEmail.text.toString().trim() else null
            val password =
                if (passwordValidator.validate()) editTextPassword.text.toString().trim() else null

            if (email != null && password != null) {
                viewModel.login(
                    email,
                    password
                )
            }
        }
    }

    override fun renderError(error: Throwable) {
        renderMessage(error.message.toString())
        if (error is BadRequestException) {
            binding.editTextEmail.requestFocus()
            binding.textInputLayoutEmail.error = getString(R.string.wrong_email)
        }
    }

    override fun renderSuccess(data: LoginStatus) {
        viewModel.navigator.toProfileScreen()
    }

}