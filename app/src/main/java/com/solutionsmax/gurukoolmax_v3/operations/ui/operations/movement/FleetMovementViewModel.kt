package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPopulateList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_movement.*
import javax.inject.Inject

class FleetMovementViewModel @Inject constructor(
    private val postFleetMovementUseCase: PostFleetMovementUseCase,
    private val amendFleetMovementUseCase: AmendFleetMovementUseCase,
    private val checkDuplicateFleetMovementUseCase: CheckDuplicateFleetMovementUseCase,
    private val fetchFleetMovementClosingRangeUseCase: FetchFleetMovementClosingRangeUseCase,
    private val fetchFleetMovementWorkflowStatusUseCase: FetchFleetMovementWorkflowStatusUseCase,
    private val populateFleetMovementListUseCase: PopulateFleetMovementListUseCase,
    private val retrieveFleetMovementDetailsUseCase: RetrieveFleetMovementDetailsUseCase,
    private val retrieveFleetMovementListUseCase: RetrieveFleetMovementListUseCase,
    private val setFleetMovementStatusUseCase: SetFleetMovementStatusUseCase
) : BaseViewModel() {

    private val _postFleetMovementMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val postFleetMovementMutableLiveData: LiveData<Int>
        get() = _postFleetMovementMutableLiveData

    private val _amendFleetMovementMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val amendFleetMovementMutableLiveData: LiveData<Int>
        get() = _amendFleetMovementMutableLiveData

    private val _checkDuplicateFleetMovementMutableLiveData: MutableLiveData<Int> =
        MutableLiveData()
    val checkDuplicateFleetMovement: LiveData<Int>
        get() = _checkDuplicateFleetMovementMutableLiveData

    private val _fetchFleetMovementClosingRangeMutableLiveData: MutableLiveData<Int> =
        MutableLiveData()
    val fetchFleetMovementClosingRangeMutableLiveData: LiveData<Int>
        get() = _fetchFleetMovementClosingRangeMutableLiveData

    private val _fetchFleetMovementWorkflowStatusMutableLiveData: MutableLiveData<Int> =
        MutableLiveData()
    val fetchFleetMovementWorkflowStatusMutableLiveData: LiveData<Int>
        get() = _fetchFleetMovementWorkflowStatusMutableLiveData

    private val _populateFleetMovementListMutableLiveData: MutableLiveData<List<FleetMovementPopulateList>> =
        MutableLiveData()
    val populateFleetMovementListMutableLiveData: LiveData<List<FleetMovementPopulateList>>
        get() = _populateFleetMovementListMutableLiveData

    private val _retrieveFleetMovementDetailsMutableLiveData: MutableLiveData<List<FleetMovementRetrieveItem>> =
        MutableLiveData()
    val retrieveFleetMovementDetailsMutableLiveData: LiveData<List<FleetMovementRetrieveItem>>
        get() = _retrieveFleetMovementDetailsMutableLiveData

    private val _retrieveFleetMovementListMutableLiveData: MutableLiveData<List<FleetMovementRetrieveItem>> =
        MutableLiveData()
    val retrieveFleetMovementListMutableLiveData: LiveData<List<FleetMovementRetrieveItem>>
        get() = _retrieveFleetMovementListMutableLiveData

    private val _setFleetMovementStatusMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val setFleetMovementStatusMutableLiveData: LiveData<Int>
        get() = _setFleetMovementStatusMutableLiveData

    fun postFleetMovement(fleetMovementPostParams: FleetMovementPostParams) {
        launchIOCoroutine {
            postFleetMovementUseCase(fleetMovementPostParams).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _postFleetMovementMutableLiveData.postValue(it)
                }
            )
        }
    }

    fun amendFleetMovement(fleetMovementPostParams: FleetMovementPostParams) = launchIOCoroutine {
        amendFleetMovementUseCase(fleetMovementPostParams).fold(
            {
                Log.d("TAG", "postFleetMovement: $it")
                ::postError
            },
            {
                _amendFleetMovementMutableLiveData.postValue(it)
            }
        )
    }

    fun checkDuplicateFleetMovement(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iVehicleID: Int,
        iOpeningReading: Int,
        dMovementDate: String
    ) =
        launchIOCoroutine {
            checkDuplicateFleetMovementUseCase(
                FleetMovementDuplicate(
                    url = url,
                    sAuthorization = sAuthorization,
                    iGroupID = iGroupID,
                    iSchoolID = iSchoolID,
                    iVehicleID = iVehicleID,
                    iOpeningReading = iOpeningReading,
                    dMovementDate = dMovementDate
                )
            ).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _checkDuplicateFleetMovementMutableLiveData.postValue(it)
                }
            )
        }

    fun fetchFleetMovementClosingRange(fleetMovementFetchWorkflowStatus: FleetMovementFetchWorkflowStatus) =
        launchIOCoroutine {
            fetchFleetMovementClosingRangeUseCase(fleetMovementFetchWorkflowStatus).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _fetchFleetMovementClosingRangeMutableLiveData.postValue(it)
                }
            )
        }

    fun fetchFleetMovementWorkflowStatus(fleetMovementFetchWorkflowStatus: FleetMovementFetchWorkflowStatus) =
        launchIOCoroutine {
            fetchFleetMovementWorkflowStatusUseCase(fleetMovementFetchWorkflowStatus).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _fetchFleetMovementWorkflowStatusMutableLiveData.postValue(it)
                }
            )
        }

    fun populateFleetMovementList(fleetMovementRetrieveList: FleetMovementRetrieveList) =
        launchIOCoroutine {
            populateFleetMovementListUseCase(fleetMovementRetrieveList).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _populateFleetMovementListMutableLiveData.postValue(it)
                }
            )
        }

    fun retrieveFleetMovementDetails(url: String, sAuthorization: String, id: Int) =
        launchIOCoroutine {
            retrieveFleetMovementDetailsUseCase(
                FleetMovementRetrieveDetails(
                    url = url,
                    sAuthorization = sAuthorization,
                    id = id
                )
            ).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _retrieveFleetMovementDetailsMutableLiveData.postValue(it)
                }
            )
        }

    fun retrieveFleetMovementList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iWorkflowStatusID: Int
    ) =
        launchIOCoroutine {
            retrieveFleetMovementListUseCase(
                FleetMovementRetrieveList(
                    url = url,
                    sAuthorization = sAuthorization,
                    iGroupID = iGroupID,
                    iSchoolID = iSchoolID,
                    iWorkflowStatusID = iWorkflowStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "postFleetMovement: $it")
                    ::postError
                },
                {
                    _retrieveFleetMovementListMutableLiveData.postValue(it)
                }
            )
        }

    fun setFleetMovementStatus(fleetMovementSetStatus: FleetMovementSetStatus) = launchIOCoroutine {
        setFleetMovementStatusUseCase(fleetMovementSetStatus).fold(
            {
                Log.d("TAG", "postFleetMovement: $it")
                ::postError
            },
            {
                _setFleetMovementStatusMutableLiveData.postValue(it)
            }
        )
    }

}