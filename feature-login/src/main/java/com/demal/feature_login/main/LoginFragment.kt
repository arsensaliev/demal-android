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
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginStatus, LoginViewModel>() {

    override var bindingNullable: FragmentLoginBinding? = null
    override val viewModel: LoginViewModel by viewModel()

    private val mAwesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

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
        initValidation()
        checkValidation()
    }

    private fun initValidation() = with(binding) {
        mAwesomeValidation.addValidation(
            textInputLayoutEmail,
            android.util.Patterns.EMAIL_ADDRESS,
            getString(R.string.wrong_email)
        )

        mAwesomeValidation.addValidation(
            textInputLayoutPassword,
            getString(R.string.regex_password_validation),
            getString(R.string.password_rule)
        )
    }

    private fun checkValidation() = with(binding) {
        buttonLogin.setOnClickListener {
            if (mAwesomeValidation.validate()) {
                // успешно
            } else {
                renderMessage(getString(R.string.input_error))
            }
        }
    }

    override fun renderSuccess(data: LoginStatus) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

}