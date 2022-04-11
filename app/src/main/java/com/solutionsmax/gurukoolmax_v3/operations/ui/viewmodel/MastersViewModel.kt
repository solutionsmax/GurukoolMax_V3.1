package com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.data.master.params.PopulateMastersParams
import com.solutionsmax.gurukoolmax_v3.core.functional.SingleLiveEvent
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.interactor.*
import javax.inject.Inject

class MastersViewModel @Inject constructor(
    private val populateMastersUseCase: PopulateMastersUseCase,
    private val populateMakeUseCase: PopulateMakeUseCase,
    private val populateManufactureYearUseCase: PopulateManufactureYearUseCase,
    private val populateFuelTypeUseCase: PopulateFuelTypeUseCase,
    private val populateHourUseCase: PopulateHourUseCase,
    private val populateMinutesUseCase: PopulateMinutesUseCase,
    private val populateTimeCycleUseCase: PopulateTimeCycleUseCase
) : BaseViewModel() {

    private val _populateMakeMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateMakeMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateMakeMutableData

    private val _populateModelMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateModelMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateModelMutableData

    private val _populateManufactureYearMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateManufactureYearMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateManufactureYearMutableData

    private val _populateFuelTypeMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateFuelTypeMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateFuelTypeMutableData

    private val _populateHoursMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateHoursMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateHoursMutableData

    private val _populateMinutesMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateMinutesMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateMinutesMutableData

    private val _populateTimeCycleMutableData: SingleLiveEvent<MutableList<PopulateMasterListItem>> =
        SingleLiveEvent()
    val populateTimeCycleMutableData: LiveData<MutableList<PopulateMasterListItem>>
        get() = _populateTimeCycleMutableData

    fun populateHours(url: String, sAuthorization: String, sTableName: String) = launchIOCoroutine {
        populateHourUseCase(
            PopulateMastersParams(
                url = url,
                sAuthorization = sAuthorization,
                sTableName = sTableName
            )
        ).fold(
            {
                postError(it)
            },
            {
                _populateHoursMutableData.postValue(it)
            }
        )
    }

    fun populateMinutes(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateMinutesUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _populateMinutesMutableData.postValue(it)
                }
            )
        }

    fun populateTimeCycle(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateTimeCycleUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _populateTimeCycleMutableData.postValue(it)
                }
            )
        }

    fun populateMake(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateMakeUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    Log.d("TAG", "master: " + it.message)
                    postError(it)
                }
            ) {
                _populateMakeMutableData.postValue(it)
            }
        }

    fun populateModel(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateMastersUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    Log.d("TAG", "master: " + it.message)
                    postError(it)
                }
            ) {
                _populateModelMutableData.postValue(it)
            }
        }

    fun populateManufactureYear(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateManufactureYearUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    Log.d("TAG", "master: " + it.message)
                    postError(it)
                }
            ) {
                _populateManufactureYearMutableData.postValue(it)
            }
        }

    fun populateFuelType(url: String, sAuthorization: String, sTableName: String) =
        launchIOCoroutine {
            populateFuelTypeUseCase(
                PopulateMastersParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sTableName = sTableName
                )
            ).fold(
                {
                    Log.d("TAG", "master: " + it.message)
                    postError(it)
                }
            ) {
                _populateFuelTypeMutableData.postValue(it)
            }
        }
}