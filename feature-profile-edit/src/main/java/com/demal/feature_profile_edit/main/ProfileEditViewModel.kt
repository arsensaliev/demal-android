package com.demal.feature_profile_edit.main

import android.content.Context
import android.net.Uri
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.user.User
import com.demal.model.data.entity.user.UserUpdate
import com.demal.repository.repository.UserRepository
import com.demal.utils.files.ContentFileUtil
import com.demal.view.core.view_model.BaseViewModel

class ProfileEditViewModel(
    val navigator: ProfileEditNavigator,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator) {

    fun init() {
        runAsync {
            val user = repository.myUser()
            mStateLiveData.value = BaseState.Success(user)
        }
    }

    fun updateProfile(id: Int, user: UserUpdate) {
        runAsync {
            repository.update(id, user)
        }
    }

    fun updateAvatar(uriPhoto: Uri, context: Context?) {
        context?.let {
            val fileByte = ContentFileUtil().getFileByte(uriPhoto, it)
            runAsync {
                repository.updateAvatar(fileByte)
            }
        }
    }

}