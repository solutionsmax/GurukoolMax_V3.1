package com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.entity.TokenLicenseItem
import com.solutionsmax.gurukoolmax_v3.remote.interactor.GetTokenLicenseUseCase
import javax.inject.Inject

class TokenLicenseViewModel @Inject constructor(
    private val getTokenLicenseUseCase: GetTokenLicenseUseCase
) : BaseViewModel() {
    private val _tokenLicenseMutableData: MutableLiveData<TokenLicenseItem> = MutableLiveData()
    val tokenLicenseMutableData: LiveData<TokenLicenseItem>
        get() = _tokenLicenseMutableData

    fun retrieveTokenLicenseInfo() = launchIOCoroutine {
        getTokenLicenseUseCase(Unit).fold(
            {
                postError(it)
            },
            {
                _tokenLicenseMutableData.postValue(it)
            }
        )
    }
}