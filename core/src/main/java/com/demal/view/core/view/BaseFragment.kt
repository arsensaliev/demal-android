package com.demal.view.core.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.AppStateEntity
import com.demal.view.core.view_model.BaseViewModel

abstract class BaseFragment<VB : ViewBinding, D : AppStateEntity, VM : BaseViewModel<D>> :
    Fragment() {

    protected abstract var bindingNullable: VB?
    protected val binding get() = bindingNullable!!
    abstract val viewModel: VM

    protected open fun renderData(state: BaseState<D>) {
        when (state) {
            is BaseState.Success -> renderSuccess(state.data)
            is BaseState.Error -> renderError(state.error)
            is BaseState.Message -> renderMessage(state.message)
            is BaseState.Loading -> showLoading(state.isLoading)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    protected abstract fun renderSuccess(data: D)

    protected open fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
    }

    protected open fun renderMessage(message: String) {
        showMessage(message)
    }

    //Может быть пустым, если экран не может отображать загрузку
    protected open fun showLoading(isLoading: Boolean) {
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}