package com.myprojects.dictionary.feature_dictionary.data.remote

import com.myprojects.dictionary.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WordInfoApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfoDto(
        @Path("word") word: String
    ): List<WordInfoDto>

    companion object {
        val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}