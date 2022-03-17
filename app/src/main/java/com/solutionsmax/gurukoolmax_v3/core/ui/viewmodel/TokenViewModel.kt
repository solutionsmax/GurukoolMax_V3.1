package com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.data.token.TokenEntity
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.interactor.GetTokenUseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem
import com.solutionsmax.gurukoolmax_v3.room.interactors.token.DeleteAllTokensUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.token.InsertTokenUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.token.PurgeTokenUseCase
import com.solutionsmax.gurukoolmax_v3.room.interactors.token.RetrieveTokensUseCase
import javax.inject.Inject

class TokenViewModel @Inject constructor(
    private val insertTokenUseCase: InsertTokenUseCase,
    private val purgeTokenUseCase: PurgeTokenUseCase,
    private val retrieveTokensUseCase: RetrieveTokensUseCase,
    private val deleteAllTokensUseCase: DeleteAllTokensUseCase,
    private val getTokenUseCase: GetTokenUseCase // Remote Token
) : BaseViewModel() {

    private val _getTokenLiveData: MutableLiveData<TokenEntity> = MutableLiveData()
    val getTokenLiveData: LiveData<TokenEntity>
        get() = _getTokenLiveData

    private val _insertTokenLiveData: MutableLiveData<Long> = MutableLiveData()
    val insertTokenLiveData: LiveData<Long>
        get() = _insertTokenLiveData

    private val _purgeTokenLiveData: MutableLiveData<Unit> = MutableLiveData()
    val purgeTokenLiveData: LiveData<Unit>
        get() = _purgeTokenLiveData

    private val _retrieveTokenLiveData: MutableLiveData<List<TokenItem>> = MutableLiveData()
    val retrieveTokenLiveData: LiveData<List<TokenItem>>
        get() = _retrieveTokenLiveData

    private val _deleteAllTokens:MutableLiveData<Unit> = MutableLiveData()
    val deleteAllTokens:LiveData<Unit>
        get() = _deleteAllTokens

    /**
     * Get token from remote
     */
    fun getRemoteToken() {
        launchIOCoroutine {
            getTokenUseCase(Unit).fold(
                {
                    Log.d("TAG", "getRemoteToken: " + it.message)
                    ::postError
                }, { tokenEntity ->
                    saveToken(tokenEntity)
                    _getTokenLiveData.postValue(tokenEntity)
                }
            )
        }
    }

    /**
     * Delete existing token before Saving Token to Local
     */
    private fun saveToken(tokenEntity: TokenEntity) {
        launchIOCoroutine {
            deleteAllTokensUseCase(
                Unit
            ).fold(
                {
                    Log.d("TAG", "purgeTokenToLocal: " + it.message)
                    ::postError
                },
                {
                    _deleteAllTokens.postValue(it)
                }
            )
            insertTokenUseCase(
                TokenItem(
                    access_token = tokenEntity.access_token,
                    token_type = tokenEntity.token_type,
                    expires_in = tokenEntity.expires_in
                )
            ).fold(
                {
                    Log.d("TAG", "saveTokenToLocal: " + it.message)
                    ::postError
                },
                {
                    _insertTokenLiveData.postValue(it)
                }
            )
        }
    }

    fun retrieveTokensFromLocal() {
        launchIOCoroutine {
            retrieveTokensUseCase(Unit).fold(
                {
                    Log.d("TAG", "retrieveTokenToLocal: " + it.message)
                    ::postError
                },
                {
                    _retrieveTokenLiveData.postValue(it)
                }
            )
        }
    }


}

