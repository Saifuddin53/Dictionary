package com.myprojects.dictionary.feature_dictionary.domain.model

import com.myprojects.dictionary.feature_dictionary.data.remote.dto.MeaningDto
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)
