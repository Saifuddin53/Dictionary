package com.myprojects.dictionary.feature_dictionary.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo
import com.myprojects.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordInfoViewModel @Inject constructor (
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {

    private val _state = mutableStateOf(WordInfoState())
    val state = _state.value

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery.value

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var searchJob: Job? = null

    fun onSearch(word: String) {
        _searchQuery.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getWordInfoUseCase(word)
                .onEach {
                    state ->
                        when(state) {
                            is Resource.Success -> {
                                _state.value = _state.value.copy(
                                    wordInfo = state.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _state.value = _state.value.copy(
                                    wordInfo = state.data ?: emptyList(),
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UIEvent.showSnackBar(
                                        message = state.message ?: "Unknown error"
                                    )
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = _state.value.copy(
                                    wordInfo = state.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                }.launchIn(this)
        }
    }

    sealed class UIEvent() {
        class showSnackBar(val message: String): UIEvent()
    }
}