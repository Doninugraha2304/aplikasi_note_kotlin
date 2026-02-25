package com.noteapp.elegant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.noteapp.elegant.data.Note
import com.noteapp.elegant.data.NoteDatabase
import com.noteapp.elegant.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>
    val notesCount: LiveData<Int>
    
    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
        notesCount = repository.notesCount
    }
    
    fun getNotesByCategory(category: String): LiveData<List<Note>> {
        return repository.getNotesByCategory(category)
    }
    
    fun getFavoriteNotes(): LiveData<List<Note>> {
        return repository.getFavoriteNotes()
    }
    
    fun searchNotes(query: String): LiveData<List<Note>> {
        return repository.searchNotes(query)
    }
    
    fun getNoteCountByCategory(category: String): LiveData<Int> {
        return repository.getNoteCountByCategory(category)
    }
    
    suspend fun getNoteById(noteId: Int): Note? {
        return repository.getNoteById(noteId)
    }
    
    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    
    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    
    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    
    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }
}
