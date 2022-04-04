package com.solutionsmax.gurukoolmax_v3.operations.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.operations.ValidateOperationsLogin
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.operations.ValidateOperationsLoginUseCase
import javax.inject.Inject

class OperationsViewModel @Inject constructor(
    private val validateOperationsLoginUseCase: ValidateOperationsLoginUseCase
) : BaseViewModel() {

    private val _validateOperationsLoginMutableData: MutableLiveData<Int> = MutableLiveData()
    val validateOperationsLoginMutableData: LiveData<Int>
        get() = _validateOperationsLoginMutableData

    fun validateOperationsLogin(
        url: String,
        sAuthorization: String,
        iGroupID:Int,
        iSchoolID:Int,
        sUsername: String,
        sPassword: String
    ) = launchIOCoroutine {
        validateOperationsLoginUseCase(
            ValidateOperationsLogin(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                sUsername = sUsername,
                sPassword = sPassword
            )
        ).fold(
            {
                postError(it)
            },
            {
                _validateOperationsLoginMutableData.postValue(it)
            }
        )
    }

}