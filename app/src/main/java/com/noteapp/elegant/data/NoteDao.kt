package com.noteapp.elegant.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    
    @Query("SELECT * FROM notes ORDER BY isPinned DESC, timestamp DESC")
    fun getAllNotes(): LiveData<List<Note>>
    
    @Query("SELECT * FROM notes WHERE category = :category ORDER BY isPinned DESC, timestamp DESC")
    fun getNotesByCategory(category: String): LiveData<List<Note>>
    
    @Query("SELECT * FROM notes WHERE isFavorite = 1 ORDER BY timestamp DESC")
    fun getFavoriteNotes(): LiveData<List<Note>>
    
    @Query("SELECT * FROM notes WHERE title LIKE :searchQuery OR content LIKE :searchQuery ORDER BY isPinned DESC, timestamp DESC")
    fun searchNotes(searchQuery: String): LiveData<List<Note>>
    
    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): Note?
    
    @Query("SELECT COUNT(*) FROM notes")
    fun getNotesCount(): LiveData<Int>
    
    @Query("SELECT COUNT(*) FROM notes WHERE category = :category")
    fun getNoteCountByCategory(category: String): LiveData<Int>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note): Long
    
    @Update
    suspend fun update(note: Note)
    
    @Delete
    suspend fun delete(note: Note)
    
    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}
