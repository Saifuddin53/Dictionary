package com.myprojects.dictionary.feature_dictionary.domain.repository

import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

}