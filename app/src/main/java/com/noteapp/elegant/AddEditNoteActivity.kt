package com.noteapp.elegant

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.noteapp.elegant.data.Category
import com.noteapp.elegant.data.Note
import com.noteapp.elegant.databinding.ActivityAddEditNoteBinding
import com.noteapp.elegant.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

class AddEditNoteActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private var currentNote: Note? = null
    private var selectedColor: Int = 0
    private var selectedCategory: String = Category.GENERAL
    private var isPinned: Boolean = false
    private var isFavorite: Boolean = false
    private var noteId: Int = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        
        // Check if editing existing note
        noteId = intent.getIntExtra("NOTE_ID", -1)
        if (noteId != -1) {
            binding.toolbar.title = "Edit Note"
            loadNote()
        } else {
            binding.toolbar.title = "Add Note"
        }
        
        setupColorPalette()
        setupCategoryChips()
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun loadNote() {
        lifecycleScope.launch {
            currentNote = noteViewModel.getNoteById(noteId)
            currentNote?.let { note ->
                binding.editTitle.setText(note.title)
                binding.editContent.setText(note.content)
                selectedColor = note.color
                selectedCategory = note.category
                isPinned = note.isPinned
                isFavorite = note.isFavorite
                updateSelectedColor(selectedColor)
                updateSelectedCategory(selectedCategory)
                invalidateOptionsMenu() // Refresh menu icons
            }
        }
    }
    
    private fun setupColorPalette() {
        val colorViews = listOf(
            binding.color0, binding.color1, binding.color2,
            binding.color3, binding.color4, binding.color5, binding.color6
        )
        
        colorViews.forEachIndexed { index, view ->
            view.setOnClickListener {
                selectedColor = index
                updateSelectedColor(index)
            }
        }
        
        // Set default selection
        updateSelectedColor(0)
    }
    
    private fun setupCategoryChips() {
        binding.categoryChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                selectedCategory = when (checkedIds[0]) {
                    R.id.chipGeneral -> Category.GENERAL
                    R.id.chipWork -> Category.WORK
                    R.id.chipPersonal -> Category.PERSONAL
                    R.id.chipIdeas -> Category.IDEAS
                    R.id.chipTodo -> Category.TODO
                    R.id.chipImportant -> Category.IMPORTANT
                    R.id.chipStudy -> Category.STUDY
                    else -> Category.GENERAL
                }
            }
        }
    }
    
    private fun updateSelectedCategory(category: String) {
        val chipId = when (category) {
            Category.WORK -> R.id.chipWork
            Category.PERSONAL -> R.id.chipPersonal
            Category.IDEAS -> R.id.chipIdeas
            Category.TODO -> R.id.chipTodo
            Category.IMPORTANT -> R.id.chipImportant
            Category.STUDY -> R.id.chipStudy
            else -> R.id.chipGeneral
        }
        binding.categoryChipGroup.check(chipId)
    }
    
    private fun updateSelectedColor(colorIndex: Int) {
        val colorViews = listOf(
            binding.color0, binding.color1, binding.color2,
            binding.color3, binding.color4, binding.color5, binding.color6
        )
        
        colorViews.forEachIndexed { index, view ->
            view.elevation = if (index == colorIndex) 8f else 0f
            view.scaleX = if (index == colorIndex) 1.1f else 1.0f
            view.scaleY = if (index == colorIndex) 1.1f else 1.0f
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        
        // Update icons based on current state
        menu.findItem(R.id.action_favorite)?.apply {
            icon = if (isFavorite) {
                ContextCompat.getDrawable(this@AddEditNoteActivity, R.drawable.ic_favorite)?.apply {
                    setTint(Color.parseColor("#FFD700")) // Gold color
                }
            } else {
                ContextCompat.getDrawable(this@AddEditNoteActivity, R.drawable.ic_favorite)?.apply {
                    setTint(Color.GRAY)
                }
            }
        }
        
        menu.findItem(R.id.action_pin)?.apply {
            icon = if (isPinned) {
                ContextCompat.getDrawable(this@AddEditNoteActivity, R.drawable.ic_pin)?.apply {
                    setTint(Color.parseColor("#FF6B6B")) // Red color
                }
            } else {
                ContextCompat.getDrawable(this@AddEditNoteActivity, R.drawable.ic_pin)?.apply {
                    setTint(Color.GRAY)
                }
            }
        }
        
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                true
            }
            R.id.action_favorite -> {
                isFavorite = !isFavorite
                invalidateOptionsMenu() // Refresh menu icons
                Toast.makeText(
                    this,
                    if (isFavorite) "Added to favorites ⭐" else "Removed from favorites",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            R.id.action_pin -> {
                isPinned = !isPinned
                invalidateOptionsMenu() // Refresh menu icons
                Toast.makeText(
                    this,
                    if (isPinned) "Note pinned 📌" else "Note unpinned",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun saveNote() {
        val title = binding.editTitle.text.toString().trim()
        val content = binding.editContent.text.toString().trim()
        
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (content.isEmpty()) {
            Toast.makeText(this, "Please enter some content", Toast.LENGTH_SHORT).show()
            return
        }
        
        val note = if (currentNote != null) {
            currentNote!!.copy(
                title = title,
                content = content,
                color = selectedColor,
                category = selectedCategory,
                isPinned = isPinned,
                isFavorite = isFavorite,
                lastModified = System.currentTimeMillis()
            )
        } else {
            Note(
                title = title,
                content = content,
                color = selectedColor,
                category = selectedCategory,
                isPinned = isPinned,
                isFavorite = isFavorite,
                timestamp = System.currentTimeMillis(),
                lastModified = System.currentTimeMillis()
            )
        }
        
        if (currentNote != null) {
            noteViewModel.update(note)
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
        } else {
            noteViewModel.insert(note)
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        }
        
        finish()
    }
    
    override fun onBackPressed() {
        val title = binding.editTitle.text.toString().trim()
        val content = binding.editContent.text.toString().trim()
        
        if (title.isNotEmpty() || content.isNotEmpty()) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Discard changes?")
                .setMessage("You have unsaved changes. Do you want to discard them?")
                .setPositiveButton("Discard") { _, _ ->
                    super.onBackPressed()
                }
                .setNegativeButton("Cancel", null)
                .show()
        } else {
            super.onBackPressed()
        }
    }
}
