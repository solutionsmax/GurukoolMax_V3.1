package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsDuplicateParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsPostParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsRetrieveListParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsRetrieveParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fuel_logs.*
import javax.inject.Inject

class FleetFuelLogsViewModel @Inject constructor(
    private val postFuelLogsUseCase: PostFuelLogsUseCase,
    private val amendFuelLogsUseCase: AmendFuelLogsUseCase,
    private val checkDuplicateFuelLogsUseCase: CheckDuplicateFuelLogsUseCase,
    private val retrieveFuelLogListUseCase: RetrieveFuelLogListUseCase,
    private val retrieveFuelLogsDetailsUseCase: RetrieveFuelLogsDetailsUseCase
) : BaseViewModel() {

    private val _postFuelLogsMutableData: MutableLiveData<Int> = MutableLiveData()
    val postFuelLogsMutableData: LiveData<Int>
        get() = _postFuelLogsMutableData

    private val _amendFuelLogsMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendFuelLogsMutableData: LiveData<Int>
        get() = _amendFuelLogsMutableData

    private val _checkDuplicateFuelLogsMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateFuelLogsMutableData: LiveData<Int>
        get() = _checkDuplicateFuelLogsMutableData

    private val _retrieveFuelLogsDetailsMutableData: MutableLiveData<List<FuelLogsRetrieveItems>> =
        MutableLiveData()
    val retrieveFuelLogsDetailsMutableData: LiveData<List<FuelLogsRetrieveItems>>
        get() = _retrieveFuelLogsDetailsMutableData

    private val _retrieveFuelLogsListMutableData: MutableLiveData<List<FuelLogsRetrieveItems>> =
        MutableLiveData()
    val retrieveFuelLogsListMutableData: LiveData<List<FuelLogsRetrieveItems>>
        get() = _retrieveFuelLogsListMutableData


    fun postFuelLogs(fuelLogsPostParams: FuelLogsPostParams) = launchIOCoroutine {
        postFuelLogsUseCase(fuelLogsPostParams).fold(
            {
                Log.d("TAG", "postFuelLog: $it")
                ::postError
            },
            {
                _postFuelLogsMutableData.postValue(it)
            }
        )
    }

    fun amendFuelLogs(fuelLogsPostParams: FuelLogsPostParams) = launchIOCoroutine {
        amendFuelLogsUseCase(fuelLogsPostParams).fold(
            {
                Log.d("TAG", "amendFuelLog: $it")
                ::postError
            },
            {
                _amendFuelLogsMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateFuelLogs(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iFleetID: Int,
        iOdometerID: Int
    ) =
        launchIOCoroutine {
            checkDuplicateFuelLogsUseCase(
                FuelLogsDuplicateParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    iGroupID = iGroupID,
                    iSchoolID = iSchoolID,
                    iFleetID = iFleetID,
                    iOdometerID = iOdometerID
                )
            ).fold(
                {
                    Log.d("TAG", "duplicateFuelLog: $it")
                    ::postError
                },
                {
                    _checkDuplicateFuelLogsMutableData.postValue(it)
                }
            )
        }

    fun retrieveFuelLogsDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ) =
        launchIOCoroutine {
            retrieveFuelLogsDetailsUseCase(
                FuelLogsRetrieveParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    id = id
                )
            ).fold(
                {
                    Log.d("TAG", "retrieveFuelLogDetails: $it")
                    ::postError
                },
                {
                    _retrieveFuelLogsDetailsMutableData.postValue(it)
                }
            )
        }

    fun retrieveFuelLogsList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolId: Int,
        iFleetID: Int,
        iWorkflowStatusID: Int
    ) = launchIOCoroutine {
        retrieveFuelLogListUseCase(
            FuelLogsRetrieveListParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolId,
                iFleetID = iFleetID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        ).fold(
            {
                Log.d("TAG", "retrieveFuelLogList: $it")
                ::postError
            },
            {
                _retrieveFuelLogsListMutableData.postValue(it)
            }
        )
    }
}