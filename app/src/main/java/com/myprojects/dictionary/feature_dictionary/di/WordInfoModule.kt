package com.myprojects.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.myprojects.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.myprojects.dictionary.feature_dictionary.data.local.util.GsonParser
import com.myprojects.dictionary.feature_dictionary.data.remote.WordInfoApi
import com.myprojects.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.myprojects.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.myprojects.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(
        repository: WordInfoRepository
    ): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: WordInfoApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(
        app: Application
    ): WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(GsonParser(Gson() ))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoApi(

    ): WordInfoApi {
        return Retrofit.Builder()
            .baseUrl(WordInfoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordInfoApi::class.java)
    }
}