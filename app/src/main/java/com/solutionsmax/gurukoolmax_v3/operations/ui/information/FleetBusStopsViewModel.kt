package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_stops.*
import javax.inject.Inject

class FleetBusStopsViewModel @Inject constructor(
    private val postBusStopsUseCase: PostBusStopsUseCase,
    private val amendBusStopsUseCase: AmendBusStopsUseCase,
    private val checkDuplicateFleetBusStopUseCase: CheckDuplicateFleetBusStopUseCase,
    private val populateFleetBusStopUseCase: PopulateFleetBusStopUseCase,
    private val retrieveBusStopDetailsUseCase: RetrieveBusStopDetailsUseCase,
    private val setFleetBusRouteStatusUseCase: SetFleetBusRouteStatusUseCase
) : BaseViewModel() {

    private val _postBusStopMutableData: MutableLiveData<Int> = MutableLiveData()
    val postBusStopMutableData: LiveData<Int>
        get() = _postBusStopMutableData

    private val _amendBusStopMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendBusStopMutableData: LiveData<Int>
        get() = _amendBusStopMutableData

    private val _checkDuplicateBusStopMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateBusStopMutableData: LiveData<Int>
        get() = _checkDuplicateBusStopMutableData

    private val _populateFleetBuStopsMutableData: MutableLiveData<List<PopulateFleetBusStopItems>> =
        MutableLiveData()
    val populateFleetBuStopsMutableData: LiveData<List<PopulateFleetBusStopItems>>
        get() = _populateFleetBuStopsMutableData

    private val _retrieveBusStopsDetailsMutableData: MutableLiveData<List<FleetBusPickupPointsList>> =
        MutableLiveData()
    val retrieveBusStopsDetailsMutableData: LiveData<List<FleetBusPickupPointsList>>
        get() = _retrieveBusStopsDetailsMutableData

    private val _setFleetBusStopsStatusMutableData: MutableLiveData<Int> = MutableLiveData()
    val setFleetBusStopsStatusMutableData: LiveData<Int>
        get() = _setFleetBusStopsStatusMutableData

    fun postBusStopInfo(postFleetBusStopParams: PostFleetBusStopParams) = launchIOCoroutine {
        postBusStopsUseCase(postFleetBusStopParams).fold(
            {
                postError(it)
            },
            {
                _postBusStopMutableData.postValue(it)
            }
        )
    }

    fun amendBusStopInfo(amendFleetBusStopParams: PostFleetBusStopParams) = launchIOCoroutine {
        amendBusStopsUseCase(amendFleetBusStopParams).fold(
            {
                postError(it)
            },
            {
                _amendBusStopMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateBusStopInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        sStopName: String
    ) = launchIOCoroutine {
        checkDuplicateFleetBusStopUseCase(
            CheckDuplicateBusStopParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iRouteID = iRouteID,
                sStopName = sStopName
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkDuplicateBusStopMutableData.postValue(it)
            }
        )
    }

    fun populateFleetBuStops(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iEditMode: Int,
        iStatusID: Int
    ) = launchIOCoroutine {
        populateFleetBusStopUseCase(
            PopulateBusStopsParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iRouteID = iRouteID,
                iEditMode = iEditMode,
                iStatusID = iStatusID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _populateFleetBuStopsMutableData.postValue(it)
            }
        )
    }

    fun retrieveBusStopsDetails(url: String, sAuthorization: String, id: Int) = launchIOCoroutine {
        retrieveBusStopDetailsUseCase(
            RetrieveBusStopsDetailsParams(
                url = url,
                sAuthorization = sAuthorization,
                id = id
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveBusStopsDetailsMutableData.postValue(it)
            }
        )
    }

    fun setFleetBusStopsStatus(url: String, sAuthorization: String, iStatusID: Int, id: Int) =
        launchIOCoroutine {
            setFleetBusRouteStatusUseCase(
                SetFleetBusStopStatusParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    iStatusID = iStatusID,
                    id = id
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _setFleetBusStopsStatusMutableData.postValue(it)
                }
            )
        }
}