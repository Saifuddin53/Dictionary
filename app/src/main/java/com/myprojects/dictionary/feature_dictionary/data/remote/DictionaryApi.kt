package com.myprojects.dictionary.feature_dictionary.data.remote

import com.myprojects.dictionary.common.BASE_URL
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.WordInfoEntity
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfoDto(
        @Path("word") word: String
    ): List<WordInfoDto>

}