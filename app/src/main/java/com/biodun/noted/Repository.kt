package com.biodun.noted

import android.util.Log
import com.biodun.noted.data.Note

open class Repository() {

    fun addNote(noteId: Int, title: String, notey: String) {
        noteList.add(Note(noteId, title, notey))
    }

    fun updateNote(noteId: Int, title: String, notey: String) {
        noteList[noteId].title = title
        noteList[noteId].noteText = notey
        Log.e("TAG", "${noteList[noteId]}")
    }

    fun getNotes() : MutableList<Note>{
        return noteList
    }

    companion object {
        @JvmField
        val noteList = ArrayList<Note>()

    }

}