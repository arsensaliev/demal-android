package com.demal.feature_profile_edit.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demal.feature_profile_edit.R
import com.demal.feature_profile_edit.databinding.FragmentProfileEditBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class ProfileEditFragmentOnlyTest :  Fragment(R.layout.fragment_profile_edit) {

    var bindingNullable: FragmentProfileEditBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileEditBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val editFirstNameLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_firstName_layout)
        val editFirstName = view.findViewById<TextInputEditText>(R.id.profile_edit_firstName)
        val firstNameMessageError = resources.getString(R.string.profile_edit_first_name_err)
        setEditorListener(editFirstNameLayout, editFirstName, firstNameMessageError)


        val editLastNameLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_surname_layout)
        val editLastName = view.findViewById<TextInputEditText>(R.id.profile_edit_surname)
        val lastNameMessageError = resources.getString(R.string.profile_edit_surname_err)
        setEditorListener(editLastNameLayout, editLastName, lastNameMessageError)

        val editCountryLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_сountry_layout)
        val editCountry = view.findViewById<TextInputEditText>(R.id.profile_edit_сountry)
        val сountryMessageError = resources.getString(R.string.profile_edit_сountry_err)
        setEditorListener(editCountryLayout, editCountry, сountryMessageError)

        val editCityLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_сity_layout)
        val editCity = view.findViewById<TextInputEditText>(R.id.profile_edit_сity)
        val сityMessageError = resources.getString(R.string.profile_edit_сity_err)
        setEditorListener(editCityLayout, editCity, сityMessageError)

    }

    private fun setEditorListener(inputLayout: TextInputLayout, inputEditText: TextInputEditText, textMessageError: String) {
        inputEditText?.setOnEditorActionListener { v, actionId, event ->
            errorMessage(inputLayout, inputEditText, textMessageError)
        }
    }

    private fun errorMessage(inputLayout: TextInputLayout, inputEditText: TextInputEditText, textMessageError: String): Boolean {
        return if (shouldShowError(inputEditText)) {
            showErrorOff(inputLayout)
            false
        } else {
            showErrorOn(inputLayout, textMessageError)
            true
        }
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





