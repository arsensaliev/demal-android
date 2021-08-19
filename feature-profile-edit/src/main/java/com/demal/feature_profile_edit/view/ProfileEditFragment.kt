package com.demal.feature_profile_edit.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.demal.feature_profile_edit.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

//Только для теста
class ProfileEditFragment : Fragment(R.layout.fragment_profile_edit) {

    private val EMPTY_STRING = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val editFirstNameLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_firstName_layout)
        val editFirstName = view.findViewById<TextInputEditText>(R.id.profile_edit_firstName)
        val firstNameMessageError = resources.getString(R.string.profile_edit_firstName_err)
        setEditorListener(editFirstNameLayout, editFirstName, firstNameMessageError)


        val editSurnameLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_surname_layout)
        val editSurname = view.findViewById<TextInputEditText>(R.id.profile_edit_surname)
        val surnameMessageError = resources.getString(R.string.profile_edit_surname_err)
        setEditorListener(editSurnameLayout, editSurname, surnameMessageError)

        val editCountryLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_сountry_layout)
        val editCountry = view.findViewById<TextInputEditText>(R.id.profile_edit_сountry)
        val сountryMessageError = resources.getString(R.string.profile_edit_сountry_err)
        setEditorListener(editCountryLayout, editCountry, сountryMessageError)

        val editCityLayout = view.findViewById<TextInputLayout>(R.id.profile_edit_сity_layout)
        val editCity = view.findViewById<TextInputEditText>(R.id.profile_edit_сity)
        val сityMessageError = resources.getString(R.string.profile_edit_сity_err)
        setEditorListener(editCityLayout, editCity, сityMessageError)

    }

    private fun setEditorListener(editFirstNameLayout: TextInputLayout, editFirstName: TextInputEditText, firstNameMessageError: String) {
        editFirstName?.setOnEditorActionListener { v, actionId, event ->
            checkErrors(editFirstNameLayout, editFirstName, firstNameMessageError)
        }
    }

    private fun checkErrors(inputLayout: TextInputLayout, editText: TextInputEditText, firstNameMessageError: String): Boolean {
        return if (shouldShowError(editText)) {
            showErrorOff(inputLayout)
            false
        } else {
            showErrorOn(inputLayout, firstNameMessageError)
            true
        }
    }

    private fun showErrorOn(editText: TextInputLayout, errText: String) {
        editText.error = errText
    }

    private fun showErrorOff(editText: TextInputLayout) {
        editText.setError(EMPTY_STRING)
    }

    private fun shouldShowError(editText: TextInputEditText): Boolean {
        if (editText.text != null) {
            val textLength = editText.text!!.length
            return textLength > 0
        }
        return false
    }

}





