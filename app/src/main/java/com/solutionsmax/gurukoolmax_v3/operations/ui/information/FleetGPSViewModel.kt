package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSPostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_gps.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_gps.*
import javax.inject.Inject

class FleetGPSViewModel @Inject constructor(
    private val postFleetGPSUseCase: PostFleetGPSUseCase,
    private val amendFleetGPSUseCase: AmendFleetGPSUseCase,
    private val checkDuplicateFleetGPSUseCase: CheckDuplicateFleetGPSUseCase,
    private val retrieveFleetGPSByDateRangeUseCase: RetrieveFleetGPSByDateRangeUseCase,
    private val retrieveFleetGPSDetailsUseCase: RetrieveFleetGPSDetailsUseCase,
    private val retrieveFleetGPSListUseCase: RetrieveFleetGPSListUseCase,
    private val retrieveFleetGPSTopListUseCase: RetrieveFleetGPSTopListUseCase
) : BaseViewModel() {

    private val _postFleetGPSMutableData: MutableLiveData<Int> = MutableLiveData()
    val postFleetGPSMutableData: LiveData<Int>
        get() = _postFleetGPSMutableData

    private val _amendFleetGPSMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendFleetGPSMutableData: LiveData<Int>
        get() = _amendFleetGPSMutableData

    private val _checkDuplicateFleetGPSMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateFleetGPSMutableData: LiveData<Int>
        get() = _checkDuplicateFleetGPSMutableData

    private val _retrieveFleetGPSByDateRangeMutableData: MutableLiveData<List<FleetGPSRetrieveItems>> =
        MutableLiveData()
    val retrieveFleetGPSByDateRangeMutableData: LiveData<List<FleetGPSRetrieveItems>>
        get() = _retrieveFleetGPSByDateRangeMutableData

    private val _retrieveFleetGPSDetailsMutableData: MutableLiveData<List<FleetGPSRetrieveItems>> =
        MutableLiveData()
    val retrieveFleetGPSDetailsMutableData: LiveData<List<FleetGPSRetrieveItems>>
        get() = _retrieveFleetGPSDetailsMutableData

    private val _retrieveFleetGPSListMutableData: MutableLiveData<List<FleetGPSRetrieveItems>> =
        MutableLiveData()
    val retrieveFleetGPSListMutableData: LiveData<List<FleetGPSRetrieveItems>>
        get() = _retrieveFleetGPSListMutableData

    private val _retrieveFleetGPSTopList: MutableLiveData<List<FleetGPSRetrieveItems>> =
        MutableLiveData()
    val retrieveFleetGPSTopList: LiveData<List<FleetGPSRetrieveItems>>
        get() = _retrieveFleetGPSTopList

    fun postFleetGPS(url: String, sAuthorization: String, fleetGPSPostItem: FleetGPSPostItem) =
        launchIOCoroutine {
            postFleetGPSUseCase(
                FleetPostGPSParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    fleetGPSPostItem = fleetGPSPostItem
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _postFleetGPSMutableData.postValue(it)
                }
            )
        }

    fun amendFleetGPS(url: String, sAuthorization: String, fleetGPSPostItem: FleetGPSPostItem) =
        launchIOCoroutine {
            amendFleetGPSUseCase(
                FleetPostGPSParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    fleetGPSPostItem = fleetGPSPostItem
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _amendFleetGPSMutableData.postValue(it)
                }
            )
        }

    fun checkDuplicateFleetGPS(
        url: String,
        sAuthorization: String,
        sAddress: String,
        sLongitude: String,
        sLatitude: String,
        sSyncDT: String,
        iRouteID: Int,
        iDriverID: Int
    ) = launchIOCoroutine {
        checkDuplicateFleetGPSUseCase(
            FleetGPSDuplicateParams(
                url = url,
                sAuthorization = sAuthorization,
                sAddress = sAddress,
                sLongitude = sLongitude,
                sLatitude = sLatitude,
                sSyncDT = sSyncDT,
                iRouteID = iRouteID,
                iDriverID = iDriverID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkDuplicateFleetGPSMutableData.postValue(it)
            }
        )
    }

    fun retrieveFleetGPSByDateRange(
        url: String,
        sAuthorization: String,
        iRouteID: Int,
        iDriverID: Int,
        dFromDate: String,
        dToDate: String
    ) = launchIOCoroutine {
        retrieveFleetGPSByDateRangeUseCase(
            FleetGPSRetrieveListByDateRangeParams(
                url = url,
                sAuthorization = sAuthorization,
                iRouteID = iRouteID,
                iDriverID = iDriverID,
                dFromDate = dFromDate,
                dToDate = dToDate
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveFleetGPSByDateRangeMutableData.postValue(it)
            }
        )
    }

    fun retrieveFleetGPSDetails(
        url: String, sAuthorization: String, id: Int
    ) = launchIOCoroutine {
        retrieveFleetGPSDetailsUseCase(
            FleetGPSRetrieveDetailsParams(
                url = url,
                sAuthorization = sAuthorization,
                id = id
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveFleetGPSDetailsMutableData.postValue(it)
            }
        )
    }

    fun retrieveFleetGPSList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iDriverID: Int
    ) = launchIOCoroutine {
        retrieveFleetGPSListUseCase(
            FleetGPSRetrieveListParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iRouteID = iRouteID,
                iDriverID = iDriverID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveFleetGPSListMutableData.postValue(it)
            }
        )
    }

    fun retrieveFleetGPSTopList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iDriverID: Int
    ) = launchIOCoroutine {
        retrieveFleetGPSTopListUseCase(
            FleetGPSRetrieveListParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iRouteID = iRouteID,
                iDriverID = iDriverID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveFleetGPSTopList.postValue(it)
            }
        )
    }
}