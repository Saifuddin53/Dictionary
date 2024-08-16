package com.myprojects.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.WordInfoEntity


@Dao
interface DictionaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(wordInfos: List<WordInfoEntity>)

    @Query("Delete from wordinfoentity where word IN(:words)")
    suspend fun deleteWords(words: List<String>)

    @Query("Select * from wordinfoentity where word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>

}