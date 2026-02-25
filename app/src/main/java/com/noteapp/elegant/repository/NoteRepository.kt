package com.noteapp.elegant.repository

import androidx.lifecycle.LiveData
import com.noteapp.elegant.data.Note
import com.noteapp.elegant.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    val notesCount: LiveData<Int> = noteDao.getNotesCount()
    
    fun getNotesByCategory(category: String): LiveData<List<Note>> {
        return noteDao.getNotesByCategory(category)
    }
    
    fun getFavoriteNotes(): LiveData<List<Note>> {
        return noteDao.getFavoriteNotes()
    }
    
    fun searchNotes(query: String): LiveData<List<Note>> {
        return noteDao.searchNotes("%$query%")
    }
    
    fun getNoteCountByCategory(category: String): LiveData<Int> {
        return noteDao.getNoteCountByCategory(category)
    }
    
    suspend fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }
    
    suspend fun insert(note: Note): Long {
        return noteDao.insert(note)
    }
    
    suspend fun update(note: Note) {
        noteDao.update(note)
    }
    
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
    
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}
