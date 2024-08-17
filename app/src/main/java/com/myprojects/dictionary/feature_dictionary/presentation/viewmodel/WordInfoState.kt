package com.myprojects.dictionary.feature_dictionary.presentation.viewmodel

import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.WordInfoEntity
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfo: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
