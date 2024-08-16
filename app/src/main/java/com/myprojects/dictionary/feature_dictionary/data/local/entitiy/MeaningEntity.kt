package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.DefinitionDto

@Entity
data class MeaningEntity(
    val definitions: List<DefinitionEntity>,
    val partOfSpeech: String
)
