package com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.data.LicenseEntity
import com.solutionsmax.gurukoolmax_v3.core.data.mapper.toItem
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.entity.FetchSiteCode
import com.solutionsmax.gurukoolmax_v3.remote.interactor.GetLicenseInfoBySiteCodeUseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import com.solutionsmax.gurukoolmax_v3.room.interactors.license.DeleteAllLicenseUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.license.InsertLicenseUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.license.PurgeLicenseUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.license.RetrieveLicenseUseCase
import javax.inject.Inject

class LicenseViewModel @Inject constructor(
    private val getLicenseInfoBySiteCodeUseCase: GetLicenseInfoBySiteCodeUseCase,
    private val insertLicenseUseCase: InsertLicenseUseCase,
    private val purgeLicenseUseCase: PurgeLicenseUseCase,
    private val deleteAllLicenseUseCase: DeleteAllLicenseUseCase,
    private val retrieveLicenseUseCase: RetrieveLicenseUseCase
) : BaseViewModel() {

    private val _fetchRestURLLiveData: MutableLiveData<String> = MutableLiveData()
    val fetchRestURLLiveData: LiveData<String>
        get() = _fetchRestURLLiveData

    private val _retrieveInfoBySideCodeLiveData: MutableLiveData<List<LicenseEntity>> =
        MutableLiveData()
    val retrieveInfoBySideCodeLiveData: LiveData<List<LicenseEntity>>
        get() = _retrieveInfoBySideCodeLiveData

    private val _insertLicenseUseCaseLiveData: MutableLiveData<Long> = MutableLiveData()
    val insertLicenseUseCaseLiveData: LiveData<Long>
        get() = _insertLicenseUseCaseLiveData

    private val _purgeLicenseInfoLiveData: MutableLiveData<Unit> = MutableLiveData()
    val purgeLicenseInfoLiveData: LiveData<Unit>
        get() = _purgeLicenseInfoLiveData

    private val _retrieveLicenseInfoUseCase: MutableLiveData<List<LicenseItem>> = MutableLiveData()
    val retrieveLicenseInfoUseCase: LiveData<List<LicenseItem>>
        get() = _retrieveLicenseInfoUseCase

    private val _deleteAllLicenseMutableData:MutableLiveData<Unit> = MutableLiveData()
    val deleteAllLicenseMutableData:LiveData<Unit>
        get() = _deleteAllLicenseMutableData

    private val _testUseCaseLiveData: MutableLiveData<List<LicenseEntity>> = MutableLiveData()
    val testUseCaseLiveData: LiveData<List<LicenseEntity>>
        get() = _testUseCaseLiveData


    fun retrieveSiteInfoBySideCode(
        sAuthorization: String,
        iStatusID: Int,
        sSiteCode: String
    ) {
        launchIOCoroutine {
            getLicenseInfoBySiteCodeUseCase(
                FetchSiteCode(
                    sAuthorization = sAuthorization,
                    iStatusID,
                    SiteCode = sSiteCode
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }
            ) {
                saveInfo(it)
                _retrieveInfoBySideCodeLiveData.postValue(it)
            }
        }
    }

    private fun saveInfo(it: List<LicenseEntity>) {
        launchIOCoroutine {
            deleteAllLicenseUseCase(Unit).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }
            ) {
                _deleteAllLicenseMutableData.postValue(it)
            }
            insertLicenseUseCase(it.first().toItem()).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }
            ) {
                _insertLicenseUseCaseLiveData.postValue(it)
            }
        }
    }

    fun retrieveLicenseInfo() {
        launchIOCoroutine {
            retrieveLicenseUseCase(Unit).fold(
                {
                    Log.d("TAG", "retrieveTokenToLocal: " + it.message)
                    ::postError
                }
            ) {
                _retrieveLicenseInfoUseCase.postValue(it)
            }
        }
    }
}