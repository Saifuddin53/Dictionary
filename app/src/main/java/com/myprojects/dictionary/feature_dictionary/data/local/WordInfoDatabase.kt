package com.myprojects.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.DefinitionEntity
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.MeaningEntity
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.PhoneticEntity
import com.myprojects.dictionary.feature_dictionary.data.local.entitiy.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: DictionaryDao


}