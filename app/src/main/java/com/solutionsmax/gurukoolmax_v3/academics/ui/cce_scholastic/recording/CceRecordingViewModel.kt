package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.RetrieveScholasticRecordingItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic.scholastic_recording.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CceRecordingViewModel @Inject constructor(
    private val postScholasticRecordingUseCase: PostScholasticRecordingUseCase,
    private val amendScholasticRecordingUseCase: AmendScholasticRecordingUseCase,
    private val checkDuplicateScholasticRecordingUseCase: CheckDuplicateScholasticRecordingUseCase,
    private val populateStudentScholasticListUseCase: PopulateStudentScholasticListUseCase,
    private val retrieveScholasticRecordingDetailsUseCase: RetrieveScholasticRecordingDetailsUseCase,
    private val retrieveScholasticRecordingListUseCase: RetrieveScholasticRecordingListUseCase
) : BaseViewModel() {

    private val _postMutableDataScholasticRecording: MutableLiveData<Int> = MutableLiveData()
    val postMutableDataScholasticRecording: LiveData<Int>
        get() = _postMutableDataScholasticRecording

    private val _amendMutableDataScholasticRecording: MutableLiveData<Int> = MutableLiveData()
    val amendMutableDataScholasticRecording: LiveData<Int>
        get() = _amendMutableDataScholasticRecording

    private val _checkDuplicatedMutableDataScholasticRecording: MutableLiveData<Int> =
        MutableLiveData()
    val checkDuplicateMutableDataScholasticRecording: LiveData<Int>
        get() = _checkDuplicatedMutableDataScholasticRecording

    private val _populateMutableDataStudentScholasticList: MutableLiveData<List<PopulateStudentScholasticGradingItem>> =
        MutableLiveData()
    val populateMutableDataStudentScholasticList: LiveData<List<PopulateStudentScholasticGradingItem>>
        get() = _populateMutableDataStudentScholasticList

    private val _retrieveMutableDataScholasticRecordingDetails: MutableLiveData<List<RetrieveScholasticRecordingItems>> =
        MutableLiveData()
    val retrieveMutableDataScholasticRecordingDetails: LiveData<List<RetrieveScholasticRecordingItems>>
        get() = _retrieveMutableDataScholasticRecordingDetails

    private val _retrieveMutableDataScholasticRecordingList: MutableLiveData<List<RetrieveScholasticRecordingItems>> =
        MutableLiveData()
    val retrieveMutableDataScholasticRecordingList: LiveData<List<RetrieveScholasticRecordingItems>>
        get() = _retrieveMutableDataScholasticRecordingList

    fun postScholasticRecording(PostScholasticRecordingParams: PostScholasticRecordingParams) =
        launchIOCoroutine {
            postScholasticRecordingUseCase(PostScholasticRecordingParams).fold(
                {
                    postError(it)
                },
                {
                    _postMutableDataScholasticRecording.postValue(it)
                }
            )
        }

    fun amendScholasticRecording(PostScholasticRecordingParams: PostScholasticRecordingParams) =
        launchIOCoroutine {
            amendScholasticRecordingUseCase(PostScholasticRecordingParams).fold(
                {
                    postError(it)
                },
                {
                    _amendMutableDataScholasticRecording.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticRecording(checkDuplicateScholasticRecordingParams: CheckDuplicateScholasticRecordingParams) =
        launchIOCoroutine {
            checkDuplicateScholasticRecordingUseCase(checkDuplicateScholasticRecordingParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicatedMutableDataScholasticRecording.postValue(it)
                }
            )
        }

    fun populateStudentScholasticList(populateStudentScholasticListParams: PopulateStudentScholasticListParams) =
        launchIOCoroutine {
            populateStudentScholasticListUseCase(populateStudentScholasticListParams).fold(
                {
                    postError(it)
                },
                {
                    _populateMutableDataStudentScholasticList.postValue(it)
                }
            )
        }

    fun retrieveScholasticRecordingDetails(retrieveScholasticRecordingDetailsParams: RetrieveScholasticRecordingDetailsParams) =
        launchIOCoroutine {
            retrieveScholasticRecordingDetailsUseCase(retrieveScholasticRecordingDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveMutableDataScholasticRecordingDetails.postValue(it)
                }
            )
        }

    fun retrieveScholasticRecordingList(retrieveScholasticRecordingListParams: RetrieveScholasticRecordingListParams) =
        launchIOCoroutine {
            retrieveScholasticRecordingListUseCase(retrieveScholasticRecordingListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveMutableDataScholasticRecordingList.postValue(result)
                    }
                )
            }
        }


}