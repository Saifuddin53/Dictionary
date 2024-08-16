package com.myprojects.dictionary.feature_dictionary.data.local.entitiy

import androidx.room.Entity

@Entity
data class PhoneticEntity(
    val audio: String,
    val text: String
)
