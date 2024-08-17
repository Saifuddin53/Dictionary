package com.myprojects.dictionary.feature_dictionary.domain.use_case

import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo
import com.myprojects.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val wordInfoRepository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow { }
        }

        return wordInfoRepository.getWordInfo(word)
    }
}