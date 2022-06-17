package com.example.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val repositry:NoteRepositry
    val allNote:LiveData<List<Note>>
    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repositry=NoteRepositry(dao)
        allNote=repositry.allNotes
    }
    fun deleteNode(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repositry.delete(note)
    }
    fun insertNode(note: Note)=viewModelScope.launch (Dispatchers.IO){
        repositry.inset(note)
    }
}