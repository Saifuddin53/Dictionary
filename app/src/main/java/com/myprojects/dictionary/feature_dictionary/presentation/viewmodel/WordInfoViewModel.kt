package com.myprojects.dictionary.feature_dictionary.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor (
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {

    private val _state = mutableStateOf(WordInfoState())
    val state = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

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
                                    wordInfoItems = state.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _state.value = _state.value.copy(
                                    wordInfoItems = state.data ?: emptyList(),
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UIEvent.ShowSnackBar(
                                        message = state.message ?: "Unknown error"
                                    )
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = _state.value.copy(
                                    wordInfoItems = state.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                }.launchIn(this)
        }
    }

    sealed class UIEvent() {
        class ShowSnackBar(val message: String): UIEvent()
    }
}