package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.RetrieveStudentReservationBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.GetFleetBusStopsUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.GetFleetPickupScheduleUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_routes.*
import javax.inject.Inject

class FleetRoutesViewModel @Inject constructor(
    private val postBusRoutesUseCase: PostBusRoutesUseCase,
    private val amendBusRoutesUseCase: AmendBusRoutesUseCase,
    private val checkDuplicateBusRouteUseCase: CheckDuplicateBusRouteUseCase,
    private val fetchBusRouteNameUseCase: FetchBusRouteNameUseCase,
    private val populateBusRouteUseCase: PopulateBusRouteUseCase,
    private val retrieveBusRoutesDetailsUseCase: RetrieveBusRoutesDetailsUseCase,
    private val retrieveStudentReservationUseCase: RetrieveStudentReservationUseCase,
    private val getFleetBusRoutesUseCase: GetFleetBusRoutesUseCase,
    private val getFleetBusStopsUseCase: GetFleetBusStopsUseCase,
    private val getFleetPickupScheduleUseCase: GetFleetPickupScheduleUseCase,
) : BaseViewModel() {

    private val _postBusRoutesMutableData: MutableLiveData<Int> = MutableLiveData()
    val postBusRoutesMutableData: LiveData<Int>
        get() = _postBusRoutesMutableData

    private val _amendBusRoutesMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendBusRoutesMutableData: LiveData<Int>
        get() = _amendBusRoutesMutableData

    private val _checkDuplicateBusRoutesMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateBusRoutesMutableData: LiveData<Int>
        get() = _checkDuplicateBusRoutesMutableData

    private val _fetchBusRouteNameMutableData: MutableLiveData<Int> = MutableLiveData()
    val fetchBusRouteNameMutableData: LiveData<Int>
        get() = _fetchBusRouteNameMutableData

    private val _populateBusRoutesMutableData: MutableLiveData<MutableList<PopulateBusRoutesItems>> =
        MutableLiveData()
    val populateBusRoutesMutableData: LiveData<MutableList<PopulateBusRoutesItems>>
        get() = _populateBusRoutesMutableData

    private val _retrieveBusRoutesDetailsMutableData: MutableLiveData<List<FleetBusRouteList>> =
        MutableLiveData()
    val retrieveBusRoutesDetailsMutableData: LiveData<List<FleetBusRouteList>>
        get() = _retrieveBusRoutesDetailsMutableData

    private val _retrieveStudentReservationMutableData: MutableLiveData<List<RetrieveStudentReservationBusRoutesItems>> =
        MutableLiveData()
    val retrieveStudentReservationMutableData: LiveData<List<RetrieveStudentReservationBusRoutesItems>>
        get() = _retrieveStudentReservationMutableData

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

    /**
     * Bus Routes
     */

    fun postBusRoute(postBusRoutesParams: PostBusRoutesParams) = launchIOCoroutine {
        postBusRoutesUseCase(postBusRoutesParams).fold(
            {
                postError(it)
            },
            {
                _postBusRoutesMutableData.postValue(it)
            }
        )
    }

    fun amendBusRoute(postBusRoutesParams: PostBusRoutesParams) = launchIOCoroutine {
        amendBusRoutesUseCase(postBusRoutesParams).fold(
            {
                postError(it)
            },
            {
                _amendBusRoutesMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateBusRoutes(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sRouteName: String
    ) = launchIOCoroutine {
        checkDuplicateBusRouteUseCase(
            DuplicateBusRoutesParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                sRouteName = sRouteName
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkDuplicateBusRoutesMutableData.postValue(it)
            }
        )
    }

    fun fetchBusRouteName(url: String, sAuthorization: String, id: Int) = launchIOCoroutine {
        fetchBusRouteNameUseCase(
            FetchBusRouteNameParams(
                url = url,
                sAuthorization = sAuthorization,
                id = id
            )
        ).fold(
            {
                postError(it)
            },
            {
                _fetchBusRouteNameMutableData.postValue(it)
            }
        )
    }

    fun populateBusRouteName(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStatusID: Int
    ) = launchIOCoroutine {
        populateBusRouteUseCase(
            PopulateBusRoutesParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iStatusID = iStatusID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _populateBusRoutesMutableData.postValue(it)
            }
        )
    }

    fun retrieveBusRoutesDetails(url: String, sAuthorization: String, id: Int) = launchIOCoroutine {
        retrieveBusRoutesDetailsUseCase(
            RetrieveBusRoutesDetailsParams(
                url = url,
                sAuthorization = sAuthorization,
                id = id
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveBusRoutesDetailsMutableData.postValue(it)
            }
        )
    }

    fun retrieveStudentReservation(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iPickupRouteID: Int,
        iDropRouteID: Int,
        iStatusID: Int
    ) = launchIOCoroutine {
        retrieveStudentReservationUseCase(
            RetrieveStudentReservationParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iPickupRouteID = iPickupRouteID,
                iDropRouteID = iDropRouteID,
                iStatusID = iStatusID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveStudentReservationMutableData.postValue(it)
            }
        )
    }

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
                    postError(it)
                }
            ) {
                _fleetRoutesMutableData.postValue(it)
            }
        }
    }

    /**
     * Bus Stop
     */

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

    /**
     * Pickup Schedule
     */

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