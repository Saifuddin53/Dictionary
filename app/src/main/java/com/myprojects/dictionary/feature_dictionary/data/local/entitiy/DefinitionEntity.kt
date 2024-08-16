package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity

@Entity
data class DefinitionEntity(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<Any>
)
