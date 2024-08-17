package com.myprojects.dictionary.feature_dictionary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.myprojects.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {
    private val _wordInfoList = MutableStateFlow<>()
}