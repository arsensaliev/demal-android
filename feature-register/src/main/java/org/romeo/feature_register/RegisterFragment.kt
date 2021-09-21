package org.romeo.feature_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.demal.feature_registration.R
import com.demal.feature_registration.databinding.FragmentRegistrationBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.EmptyEntity
import com.demal.model.data.entity.user.User
import com.demal.model.data.exceptions.UserAlreadyExistsException
import com.demal.view.core.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment
    : BaseFragment<FragmentRegistrationBinding, User, RegisterViewModel>() {
    override var bindingNullable: FragmentRegistrationBinding? = null

    override val viewModel: RegisterViewModel by viewModel()

    private lateinit var validator: AwesomeValidation

    private fun initValidator() =
        AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
            .apply {
                validator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
                setTextInputLayoutErrorTextAppearance(R.style.errorTextInputLayout)

                validator.addValidation(
                    binding.textInputLayoutEmail,
                    RegexTemplate.NOT_EMPTY,
                    getString(R.string.profile_edit_email_err)
                )

                validator.addValidation(
                    binding.textInputLayoutPassword,
                    RegexTemplate.NOT_EMPTY,
                    getString(R.string.profile_edit_password_err)
                )

                validator.addValidation(
                    binding.textInputLayoutFirstName,
                    RegexTemplate.NOT_EMPTY,
                    getString(R.string.profile_edit_first_name_err)
                )

                validator.addValidation(
                    binding.textInputLayoutLastName,
                    RegexTemplate.NOT_EMPTY,
                    getString(R.string.profile_edit_last_name_err)
                )
            }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRegistrationBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        initValidator()
    }

    private fun setListeners() {
        binding.buttonRegister.setOnClickListener {
            if (validator.validate())
                viewModel.register(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString(),
                    binding.editTextFirstName.text.toString(),
                    binding.editTextLastName.text.toString()
                )
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.navigateToLoginScreen()
        }
    }

    override fun renderSuccess(data: User) {
        super.renderSuccess(data)
        binding.editTextEmail.setText(data.email)
        binding.editTextFirstName.setText(data.firstName)
        binding.editTextLastName.setText(data.lastName)
    }

    override fun renderError(error: Throwable) {
        if (error is UserAlreadyExistsException) {
            showUserAlreadyExistsError()
        }
    }

    private fun showUserAlreadyExistsError() {
        binding.textInputLayoutEmail.error =
            getString(R.string.user_already_exists_error)
    }
}