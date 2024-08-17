package com.myprojects.dictionary.feature_dictionary.data.repository

import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.data.local.WordInfoDao
import com.myprojects.dictionary.feature_dictionary.data.remote.WordInfoApi
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo
import com.myprojects.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api: WordInfoApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfoDto(word)
            dao.deleteWords(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = wordInfos
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}