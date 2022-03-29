package com.biodun.domore

import androidx.lifecycle.*
import com.biodun.domore.data.Note
import com.biodun.domore.data.NoteDao
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteDao: NoteDao) : ViewModel() {

    val allNotes: LiveData<List<Note>> = noteDao.getNoteList().asLiveData()

    fun retrieveNote(id: Int): LiveData<Note>{
        return noteDao.getNote(id).asLiveData()
    }

    private fun insertNote(note: Note){
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    private fun updateNote(note: Note){
        viewModelScope.launch {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }




    private fun getNewNoteEntry(noteTitle: String, noteText: String) : Note{
        return Note(
            noteTitle = noteTitle,
            noteText = noteText
        )
    }


    fun addNewNote(noteTitle: String, noteText: String){
        val newNote = getNewNoteEntry(noteTitle, noteText)
        insertNote(newNote)
    }

//    private fun getUpdatedNote

    fun updateNote(noteId: Int, noteTitle: String, noteText: String){
        val updatedNote = Note(noteId, noteTitle, noteText)
        updateNote(updatedNote)
    }


    fun isEntryValid(noteTitle: String, noteText: String) : Boolean{
        if (noteTitle.isBlank() || noteText.isBlank()){
            return false
        }
        return true
    }

}

class NoteViewModelFactory(private val noteDao: NoteDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}