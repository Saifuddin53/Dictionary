package com.myprojects.dictionary.feature_dictionary.data.remote

import com.myprojects.dictionary.common.BASE_URL
import retrofit2.http.GET

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getMeaning(word: String)

}