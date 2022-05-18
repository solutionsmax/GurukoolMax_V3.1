package com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.entity.SettingsParams
import com.solutionsmax.gurukoolmax_v3.remote.interactor.FetchSemesterInfoUseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem
import com.solutionsmax.gurukoolmax_v3.room.interactors.setting.*
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val fetchSemesterInfoUseCase: FetchSemesterInfoUseCase,
    private val postSettingsUseCase: PostSettingsUseCase,
    private val amendSettingsUseCase: AmendSettingsUseCase,
    private val purgeSettingsUseCase: PurgeSettingsUseCase,
    private val deleteAllSettingsUseCase: DeleteAllSettingsUseCase,
    private val retrieveSettingsUseCase: RetrieveSettingsUseCase
) : BaseViewModel() {

    private val _fetchMutableSemesterInfo: MutableLiveData<Int> = MutableLiveData()
    val fetchMutableSemesterInfo: LiveData<Int>
        get() = _fetchMutableSemesterInfo

    private val _insertSettingLiveData: MutableLiveData<Long> = MutableLiveData()
    val insertSettingLiveData: LiveData<Long>
        get() = _insertSettingLiveData

    private val _purgeSettingLiveData: MutableLiveData<Unit> = MutableLiveData()
    val purgeSettingLiveData: LiveData<Unit>
        get() = _purgeSettingLiveData

    private val _purgeAllSettingLiveData: MutableLiveData<Unit> = MutableLiveData()
    val purgeAllSettingLiveData: LiveData<Unit>
        get() = _purgeAllSettingLiveData

    private val _retrieveSettingsLiveData: MutableLiveData<List<SettingsItem>> = MutableLiveData()
    val retrieveSettingsLiveData: LiveData<List<SettingsItem>>
        get() = _retrieveSettingsLiveData

    fun fetchSemesterInfo(settingsParams: SettingsParams) = launchIOCoroutine {
        fetchSemesterInfoUseCase(settingsParams).fold(
            {
                postError(it)
            },
            {
                saveSemesterInfo(SettingsItem(setting_id = it))
                _fetchMutableSemesterInfo.postValue(it)
            }
        )
    }

    fun saveSemesterInfo(settingsParams: SettingsItem) = launchIOCoroutine {
        deleteAllSettingsUseCase(
            Unit
        ).fold(
            {
                Log.d("TAG", "purgeTokenToLocal: " + it.message)
                postError(it)
            }
        ) {
            _purgeAllSettingLiveData.postValue(it)
        }
        postSettingsUseCase(
            SettingsItem(
                setting_id = settingsParams.setting_id
            )
        ).fold(
            {
                Log.d("TAG", "saveTokenToLocal: " + it.message)
                postError(it)
            }
        ) {
            _insertSettingLiveData.postValue(it)
        }
    }

    fun retrieveSettings() = launchIOCoroutine {
        retrieveSettingsUseCase(Unit).fold(
            {
                postError(it)
            },
            {
                _retrieveSettingsLiveData.postValue(it)
            }
        )
    }
}