package com.demal.feature_login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var email: String
    private lateinit var password: String

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val passwordLength = 13
    lateinit var textInputEmail: TextInputLayout
    lateinit var textInputPassword: TextInputLayout
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textInputEmail = view.findViewById(R.id.text_input_layout_email)
        textInputPassword = view.findViewById(R.id.text_input_layout_password)
        editTextEmail = view.findViewById(R.id.edit_text_email)
        editTextPassword = view.findViewById(R.id.edit_text_password)

        editTextEmail.addTextChangedListener(TextFieldValidation(editTextEmail))
        editTextPassword.addTextChangedListener(TextFieldValidation(editTextPassword))

        val buttonLogin = view.findViewById<MaterialButton>(R.id.button_login)
        buttonLogin.setOnClickListener {
            if (isValidate()) {
                // переход на следующий экран
            } else {
                // сообщение об ошибке
            }

        }
    }

    private fun isValidate(): Boolean = isValidEmail() && isValidatePassword()

    private fun isValidEmail(): Boolean =
        if (!editTextEmail.text!!.trim().matches(emailPattern.toRegex())) {
            textInputEmail.error = "Некорректный email"
            editTextEmail.requestFocus()
            false
        } else {
            textInputEmail.isErrorEnabled = false
            email = editTextEmail.text.toString()
            true
        }


    private fun isValidatePassword(): Boolean =
        if (editTextPassword.text.toString().trim().length < passwordLength) {
            textInputPassword.error = "getString()"
            editTextPassword.requestFocus()
            false

        } else {
            textInputPassword.isErrorEnabled = false
            password = editTextPassword.text.toString()
            true
        }

    companion object {
        fun newInstance() = LoginFragment()
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            when (view.id) {
                R.id.edit_text_email -> {
                    isValidEmail()
                }
                R.id.edit_text_password -> {
                    isValidatePassword()
                }
            }
        }
    }
}