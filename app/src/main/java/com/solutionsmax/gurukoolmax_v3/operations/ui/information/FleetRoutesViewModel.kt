package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusPickupScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.FleetBusStopsParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.GetFleetBusRoutesUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.GetFleetBusStopsUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.GetFleetPickupScheduleUseCase
import javax.inject.Inject

class FleetRoutesViewModel @Inject constructor(
    private val getFleetBusRoutesUseCase: GetFleetBusRoutesUseCase,
    private val getFleetBusStopsUseCase: GetFleetBusStopsUseCase,
    private val getFleetPickupScheduleUseCase: GetFleetPickupScheduleUseCase,
) : BaseViewModel() {

    private val _fleetRoutesMutableData: MutableLiveData<List<FleetBusRouteList>> =
        MutableLiveData()
    val fleetRoutesMutableData: LiveData<List<FleetBusRouteList>>
        get() = _fleetRoutesMutableData

    private val _fleetBusStopMutableData: MutableLiveData<List<FleetBusPickupPointsList>> =
        MutableLiveData()
    val fleetBusStopMutableData: LiveData<List<FleetBusPickupPointsList>>
        get() = _fleetBusStopMutableData

    private val _fleetPickupScheduleMutableData: MutableLiveData<List<FleetPickupScheduleList>> =
        MutableLiveData()
    val fleetPickupScheduleMutableData: LiveData<List<FleetPickupScheduleList>>
        get() = _fleetPickupScheduleMutableData


    fun retrieveFleetBusRoutes(url: String, sAuthorizationKey: String, iStatusID: Int) {
        launchIOCoroutine {
            getFleetBusRoutesUseCase(
                FleetBusRoutesParams(
                    url = url,
                    sAuthorization = sAuthorizationKey,
                    iStatusID = iStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }
            ) {
                _fleetRoutesMutableData.postValue(it)
            }
        }
    }

    fun retrieveFleetBusStop(url: String, sAuthorizationKey: String, iStatusID: Int) {
        launchIOCoroutine {
            getFleetBusStopsUseCase(
                FleetBusStopsParams(
                    url = url,
                    sAuthorization = sAuthorizationKey,
                    iStatusID = iStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                }
            ) {
                _fleetBusStopMutableData.postValue(it)
            }
        }
    }

    fun retrieveFleetPickupSchedule(
        url: String,
        sAuthorizationKey: String,
        iRouteID: Int,
        iStatusID: Int
    ) {
        launchIOCoroutine {
            getFleetPickupScheduleUseCase(
                FleetBusPickupScheduleParams(
                    url = url,
                    sAuthorization = sAuthorizationKey,
                    iRouteID = iRouteID,
                    iStatusID = iStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }
            ) {
                _fleetPickupScheduleMutableData.postValue(it)
            }
        }
    }
}