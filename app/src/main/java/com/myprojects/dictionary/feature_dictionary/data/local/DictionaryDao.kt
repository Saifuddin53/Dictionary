package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface DictionaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(wordInfos: List<WordInfoEntity>)
}