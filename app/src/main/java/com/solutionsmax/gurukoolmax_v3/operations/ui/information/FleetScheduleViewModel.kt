package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.CheckDuplicateFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.RetrieveDetailsFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule.AmendBusScheduleUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule.CheckDuplicateBusScheduleUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule.PostBusScheduleUseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.bus_schedule.RetrieveBusPickupScheduleDetailsUseCase
import javax.inject.Inject

class FleetScheduleViewModel @Inject constructor(
    private val postBusScheduleUseCase: PostBusScheduleUseCase,
    private val amendBusStopsUseCase: AmendBusScheduleUseCase,
    private val checkDuplicateBusScheduleUseCase: CheckDuplicateBusScheduleUseCase,
    private val retrieveBusPickupScheduleDetailsUseCase: RetrieveBusPickupScheduleDetailsUseCase
) : BaseViewModel() {

    private val _postBusScheduleMutableData: MutableLiveData<Int> = MutableLiveData()
    val postBusScheduleMutableData: LiveData<Int>
        get() = _postBusScheduleMutableData

    private val _amendBusScheduleMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendBusScheduleMutableData: LiveData<Int>
        get() = _amendBusScheduleMutableData

    private val _checkDuplicateBusScheduleMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateBusScheduleMutableData: LiveData<Int>
        get() = _checkDuplicateBusScheduleMutableData

    private val _retrieveBusScheduleDetailsMutableData: MutableLiveData<List<FleetPickupScheduleList>> =
        MutableLiveData()
    val retrieveBusScheduleDetailsMutableData: LiveData<List<FleetPickupScheduleList>>
        get() = _retrieveBusScheduleDetailsMutableData

    private val _setStatusBusScheduleMutableData: MutableLiveData<Int> = MutableLiveData()
    val setStatusBusScheduleMutableData: LiveData<Int>
        get() = _setStatusBusScheduleMutableData

    fun postBusScheduleInfo(postInfo: PostFleetBusScheduleParams) = launchIOCoroutine {
        postBusScheduleUseCase(postInfo).fold(
            {
                postError(it)
            },
            {
                _postBusScheduleMutableData.postValue(it)
            }
        )
    }

    fun amendBusScheduleInfo(amend: PostFleetBusScheduleParams) = launchIOCoroutine {
        amendBusStopsUseCase(amend).fold(
            {
                postError(it)
            },
            {
                _amendBusScheduleMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateBusScheduleInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iStopID: Int
    ) = launchIOCoroutine {
        checkDuplicateBusScheduleUseCase(
            CheckDuplicateFleetBusScheduleParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iRouteID = iRouteID,
                iStopID = iStopID
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkDuplicateBusScheduleMutableData.postValue(it)
            }
        )
    }

    fun retrieveBusScheduleDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ) = launchIOCoroutine {
        retrieveBusPickupScheduleDetailsUseCase(
            RetrieveDetailsFleetBusScheduleParams(
                url = url,
                sAuthorization = sAuthorization,
                id = id
            )
        ).fold(
            {
                postError(it)
            },
            {
                _retrieveBusScheduleDetailsMutableData.postValue(it)
            }
        )
    }

}