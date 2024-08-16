package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.MeaningDto
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.PhoneticDto

@Entity
data class WordInfoEntity(
    val meanings: List<MeaningEntity>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String,
    @PrimaryKey val id: Int? = null
)
