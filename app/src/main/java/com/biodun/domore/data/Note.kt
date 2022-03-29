package com.biodun.domore.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var noteTitle: String,

    @ColumnInfo(name = "note")
    var noteText: String
)
//    : Serializable
