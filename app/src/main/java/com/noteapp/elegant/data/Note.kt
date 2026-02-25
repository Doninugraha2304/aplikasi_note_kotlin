package com.noteapp.elegant.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis(),
    val color: Int = 0, // 0-6 for different colors
    val isPinned: Boolean = false,
    val category: String = Category.GENERAL,
    val isFavorite: Boolean = false
) {
    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
    
    fun getFormattedModifiedDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return sdf.format(Date(lastModified))
    }
    
    fun getWordCount(): Int {
        return content.trim().split("\\s+".toRegex()).size
    }
    
    fun getCharCount(): Int {
        return content.length
    }
}

object Category {
    const val ALL = "All"
    const val GENERAL = "General"
    const val WORK = "Work"
    const val PERSONAL = "Personal"
    const val IDEAS = "Ideas"
    const val TODO = "To-Do"
    const val IMPORTANT = "Important"
    const val STUDY = "Study"
    
    val categories = listOf(ALL, GENERAL, WORK, PERSONAL, IDEAS, TODO, IMPORTANT, STUDY)
    
    fun getCategoryIcon(category: String): String {
        return when(category) {
            WORK -> "💼"
            PERSONAL -> "👤"
            IDEAS -> "💡"
            TODO -> "✅"
            IMPORTANT -> "⭐"
            STUDY -> "📚"
            else -> "📝"
        }
    }
    
    fun getCategoryColor(category: String): Int {
        return when(category) {
            WORK -> 1
            PERSONAL -> 2
            IDEAS -> 3
            TODO -> 4
            IMPORTANT -> 5
            STUDY -> 6
            else -> 0
        }
    }
}
