package com.demal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demal.model.dto.auth.AuthenticationResultDto
import com.demal.repository.Repository
import com.demal.repository.RetrofitImplementation
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAuth("vladimir@mail.ru", "password")

    }

    private fun authResult(auth: AuthenticationResultDto) {
        val token = auth.auth?.token
        val email = auth.user?.email
        val firstName = auth.user?.firstName
        val lastName = auth.user?.lastName

    }

    private fun getAuth(email: String, pass: String) {

        var repository: Repository = RetrofitImplementation()
        viewModelCoroutineScope.launch {
            authResult(repository.getAuthData(email, pass))

        }
    }

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun handleError(error: Throwable) {
        Toast.makeText(
            applicationContext,
            "error $error.message",
            Toast.LENGTH_SHORT
        ).show()
    }
}