package com.example.note

import androidx.lifecycle.LiveData

class NoteRepositry(private val noteDao: NoteDao) {
    val allNotes:LiveData<List<Note>> =noteDao.getAllNotes()
    suspend fun inset(note: Note){
        noteDao.insert(note)
    }
    suspend fun  delete(note: Note){
        noteDao.delete(note)
    }
}