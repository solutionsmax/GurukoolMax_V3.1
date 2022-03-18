package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.SingleLiveEvent
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register.*
import javax.inject.Inject

class RegisteredFleetViewModel @Inject constructor(
    private val postRegisteredFleetInfoUseCaseUseCase: PostRegisteredFleetInfoUseCase,
    private val amendRegisteredFleetUseCase: AmendRegisteredFleetUseCase,
    private val checkRegisteredFleetDuplicateInfoUseCase: CheckRegisteredFleetDuplicateInfoUseCase,
    private val fetchRegisteredFleetPhotoUseCase: FetchRegisteredFleetPhotoUseCase,
    private val populateRegisteredFleetUseCase: PopulateRegisteredFleetUseCase,
    private val postRegisteredFleetPhotoUseCase: PostRegisteredFleetPhotoUseCase,
    private val retrieveRegisteredFleetDetailsUseCase: RetrieveRegisteredFleetDetailsUseCase,
    private val retrieveRegisteredFleetListUseCase: RetrieveRegisteredFleetListUseCase,
    private val setRegisteredFleetStatusUseCase: SetRegisteredFleetStatusUseCase
) : BaseViewModel() {

    private val _postRegisteredFleetMutableData: MutableLiveData<Int> = MutableLiveData()
    val postRegisteredFleetMutableData: LiveData<Int>
        get() = _postRegisteredFleetMutableData

    private val _amendRegisteredFleetMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendRegisteredFleetMutableData: LiveData<Int>
        get() = _amendRegisteredFleetMutableData

    private val _checkRegisteredFleetDuplicateMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkRegisteredFleetDuplicateMutableData: LiveData<Int>
        get() = _checkRegisteredFleetDuplicateMutableData

    private val _fetchRegisteredFleetPhotoMutableLData: MutableLiveData<String> = MutableLiveData()
    val fetchRegisteredFleetPhotoMutableLData: LiveData<String>
        get() = _fetchRegisteredFleetPhotoMutableLData

    private val _populateRegisteredFleetMutableData: MutableLiveData<MutableList<PopulateRegisteredFleetList>> =
        MutableLiveData()
    val populateRegisteredFleetMutableData: LiveData<MutableList<PopulateRegisteredFleetList>>
        get() = _populateRegisteredFleetMutableData

    private val _postRegisteredFleetPhotoMutableData: MutableLiveData<Int> = MutableLiveData()
    val postRegisteredFleetPhotoMutableData: LiveData<Int>
        get() = _postRegisteredFleetPhotoMutableData

    private val _retrieveRegisteredFleetDetailsMutableData: MutableLiveData<List<FleetRegisterRetrieveListItem>> =
        MutableLiveData()
    val retrieveRegisteredFleetDetailsMutableData: LiveData<List<FleetRegisterRetrieveListItem>>
        get() = _retrieveRegisteredFleetDetailsMutableData

    private val _retrieveRegisteredFleetListMutableData: MutableLiveData<List<FleetRegisterRetrieveListItem>> =
        MutableLiveData()
    val retrieveRegisteredFleetListMutableData: LiveData<List<FleetRegisterRetrieveListItem>>
        get() = _retrieveRegisteredFleetListMutableData

    private val _setRegisteredFleetMutableData: MutableLiveData<Int> = MutableLiveData()
    val setRegisteredFleetMutableData: LiveData<Int>
        get() = _setRegisteredFleetMutableData

    private val _stateLiveData: MutableLiveData<ViewState> = MutableLiveData()
    val stateLiveData: LiveData<ViewState>
        get() = _stateLiveData

    init {
        _stateLiveData.value = ViewState.Default
    }

    // Cannot leave the form if the State is DataSaving
    fun isCanLeave(): Boolean = _stateLiveData.value != ViewState.DataSaving

    fun postRegisteredFleetInfo(fleetRegisterPostInfoItem: FleetRegisterPostParams) {
        launchIOCoroutine {
            /*checkAllDataFilled()
            postState(ViewState.DataSaving)*/
            postRegisteredFleetInfoUseCaseUseCase(
                params = fleetRegisterPostInfoItem
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                    postState(ViewState.DataSaveFailed(it))
                },
                {
                    postState(ViewState.DataSaved)
                    _postRegisteredFleetMutableData.postValue(it)
                }
            )
        }
    }

    private fun postState(state: ViewState) {
        _stateLiveData.postValue(state)
    }

    fun amendRegisteredFleetInfo(fleetRegisterPostInfoItem: FleetRegisterPostParams) {
        launchIOCoroutine {
            amendRegisteredFleetUseCase(
                params = fleetRegisterPostInfoItem
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                },
                {
                    _amendRegisteredFleetMutableData.postValue(it)
                }
            )
        }
    }

    fun checkRegisteredFleetDuplicateInfo(
        url: String,
        sAuthorization: String,
        sRegistrationNumber: String
    ) {
        launchIOCoroutine {
            checkRegisteredFleetDuplicateInfoUseCase(
                FleetDuplicateCheckParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    sRegistrationNumber = sRegistrationNumber
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                },
                {
                    _checkRegisteredFleetDuplicateMutableData.postValue(it)
                }
            )
        }
    }

    fun fetchRegisteredFleetPhotoInfo(fleetRegistered: FetchRegisteredFleetPhoto) {
        launchIOCoroutine {
            fetchRegisteredFleetPhotoUseCase(params = fleetRegistered).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                },
                {
                    _fetchRegisteredFleetPhotoMutableLData.postValue(it)
                }
            )
        }
    }

    fun populateRegisteredFleetList(
        url: String,
        sAuthorization: String,
        iWorkflowStatusID: Int
    ) {
        launchIOCoroutine {
            populateRegisteredFleetUseCase(
                FleetRegisteredListParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    iWorkflowStatusID = iWorkflowStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                },
                {
                    _populateRegisteredFleetMutableData.postValue(it)
                }
            )
        }
    }

    fun postRegisteredFleetPhotoUseCase(fleetRegisterPostInfoItem: FleetRegisterPostParams) {
        launchIOCoroutine {
            postRegisteredFleetPhotoUseCase(params = fleetRegisterPostInfoItem).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                },
                {
                    _postRegisteredFleetPhotoMutableData.postValue(it)
                }
            )
        }
    }

    fun retrieveRegisteredFleetDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ) {
        launchIOCoroutine {
            retrieveRegisteredFleetDetailsUseCase(
                FleetRegisteredDetailsParams(
                    url = url, sAuthorization = sAuthorization, id = id
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                },
                {
                    _retrieveRegisteredFleetDetailsMutableData.postValue(it)
                }
            )
        }
    }

    fun retrieveRegisteredFleetList(url: String, sAuthorization: String, iWorkflowStatusID: Int) {
        launchIOCoroutine {
            retrieveRegisteredFleetListUseCase(
                FleetRegisteredListParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    iWorkflowStatusID = iWorkflowStatusID
                )
            ).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    postError(it)
                },
                {
                    _retrieveRegisteredFleetListMutableData.postValue(it)
                }
            )
        }
    }

    fun setRetrievedRegisteredFleetInfo(setRegisteredFleetStatus: SetRegisteredFleetStatus) {
        launchIOCoroutine {
            setRegisteredFleetStatusUseCase(params = setRegisteredFleetStatus).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                },
                {
                    _setRegisteredFleetMutableData.postValue(it)
                }
            )
        }
    }
}


sealed class ViewState {
    object Default : ViewState()
    object DataLoading : ViewState()
    object DataLoadedSuccess : ViewState()
    object DataLoadedFailed : ViewState()
    class DataNotFilled(
        val vehicleName: Boolean,
        val registrationNumber: Boolean,
        val model: Boolean,
        val make: Boolean,
    ) : ViewState()

    object DataSaving : ViewState()
    object DataSaved : ViewState()
    class DataSaveFailed(val failure: Failure) : ViewState()
}