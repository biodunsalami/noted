package com.biodun.domore.data

import java.io.Serializable


data class Note(
    var noteId: Int = 0,
    var title: String,
    var noteText: String
) : Serializable
