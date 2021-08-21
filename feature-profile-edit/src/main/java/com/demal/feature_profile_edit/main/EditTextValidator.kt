package com.demal.feature_profile_edit.main

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditTextValidator(var vainputLayout: TextInputLayout?, var inputEditText: TextInputEditText?, var textMessageError: String) {

    init{
        setEditorListener()
    }

    fun checkForError(): Boolean {
        return errorMessage(
            vainputLayout,
            inputEditText,
            textMessageError
        )
    }

    private fun setEditorListener() {
        inputEditText?.setOnEditorActionListener { _, _, _ ->
            checkForError()
        }
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
        inputLayout.error = null
    }

    private fun shouldShowError(inputEditText: TextInputEditText): Boolean {
        if (inputEditText.text != null) {
            val textLength = inputEditText.text!!.length
            return textLength > 0
        }
        return false
    }

}