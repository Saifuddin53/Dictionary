package com.myprojects.dictionary.feature_dictionary.data.repository

import com.myprojects.dictionary.core.util.Resource
import com.myprojects.dictionary.feature_dictionary.data.local.WordInfoDao
import com.myprojects.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.myprojects.dictionary.feature_dictionary.domain.model.WordInfo
import com.myprojects.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
    private val wordInfoDao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfoLocal = wordInfoDao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfoLocal))

        try {
            val newWordInfo = dictionaryApi.getWordInfoDto(word)
            wordInfoDao.deleteWords(newWordInfo.map { it.word })
            wordInfoDao.insertWordInfos(newWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = wordInfoLocal
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Not connected to the internet",
                    data = wordInfoLocal
                )
            )
        }

        val newWordInfo = wordInfoDao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(data = newWordInfo))

    }
}