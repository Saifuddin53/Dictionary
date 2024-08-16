package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity
import com.myprojects.dictionary.feature_dictionary.domain.model.Definition

@Entity
data class DefinitionEntity(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<Any>
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}
