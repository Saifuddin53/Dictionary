package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity
import com.myprojects.dictionary.feature_dictionary.data.remote.dto.DefinitionDto
import com.myprojects.dictionary.feature_dictionary.domain.model.Meaning

@Entity
data class MeaningEntity(
    val definitions: List<DefinitionEntity>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}
