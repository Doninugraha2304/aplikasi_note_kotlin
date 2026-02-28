package com.noteapp.elegant

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.noteapp.elegant.adapter.NoteAdapter
import com.noteapp.elegant.data.Category
import com.noteapp.elegant.data.Note
import com.noteapp.elegant.databinding.ActivityMainBinding
import com.noteapp.elegant.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private var currentCategory = Category.ALL
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        
        // Setup RecyclerView
        noteAdapter = NoteAdapter(
            onNoteClick = { note ->
                val intent = Intent(this, AddEditNoteActivity::class.java)
                intent.putExtra("NOTE_ID", note.id)
                startActivity(intent)
            },
            onNoteLongClick = { note ->
                showNoteOptionsDialog(note)
            },
            onFavoriteClick = { note ->
                toggleFavorite(note)
            },
            onPinClick = { note ->
                togglePin(note)
            },
            onDeleteSwipe = { note ->
                deleteNoteWithUndo(note)
            }
        )
        
        binding.recyclerView.apply {
            adapter = noteAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        
        // Setup swipe to delete
        setupSwipeToDelete()
        
        // Setup ViewModel
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        
        // Observe all notes for dashboard
        noteViewModel.allNotes.observe(this) { notes ->
            updateDashboard(notes)
        }
        
        // Setup Category Chips
        setupCategoryChips()
        
        // Load initial category
        loadNotesByCategory(Category.ALL)
        
        // Setup FAB
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
        
        // Setup Dashboard Card Animations
        setupDashboardAnimations()
        
        // Setup Search
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isEmpty()) {
                    loadNotesByCategory(currentCategory)
                } else {
                    searchNotes(query)
                }
            }
        })
    }
    
    private fun setupCategoryChips() {
        binding.categoryChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                currentCategory = when (checkedIds[0]) {
                    R.id.chipAll -> Category.ALL
                    R.id.chipGeneral -> Category.GENERAL
                    R.id.chipWork -> Category.WORK
                    R.id.chipPersonal -> Category.PERSONAL
                    R.id.chipIdeas -> Category.IDEAS
                    R.id.chipTodo -> Category.TODO
                    R.id.chipImportant -> Category.IMPORTANT
                    R.id.chipStudy -> Category.STUDY
                    else -> Category.ALL
                }
                loadNotesByCategory(currentCategory)
            }
        }
    }
    
    private fun loadNotesByCategory(category: String) {
        val liveData = if (category == Category.ALL) {
            noteViewModel.allNotes
        } else {
            noteViewModel.getNotesByCategory(category)
        }
        
        liveData.observe(this) { notes ->
            notes?.let { 
                noteAdapter.submitList(it)
                updateEmptyView(it.isEmpty())
            }
        }
    }
    
    private fun searchNotes(query: String) {
        noteViewModel.searchNotes(query).observe(this) { notes ->
            noteAdapter.submitList(notes)
            updateEmptyView(notes.isEmpty())
        }
    }
    
    private fun updateDashboard(notes: List<Note>) {
        binding.totalNotesCount.text = notes.size.toString()
        binding.pinnedNotesCount.text = notes.count { it.isPinned }.toString()
        binding.favoriteNotesCount.text = notes.count { it.isFavorite }.toString()
    }
    
    private fun updateEmptyView(isEmpty: Boolean) {
        if (isEmpty) {
            binding.emptyView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
    
    private fun toggleFavorite(note: Note) {
        val updatedNote = note.copy(
            isFavorite = !note.isFavorite,
            lastModified = System.currentTimeMillis()
        )
        noteViewModel.update(updatedNote)
        Toast.makeText(
            this,
            if (updatedNote.isFavorite) "Added to favorites ⭐" else "Removed from favorites",
            Toast.LENGTH_SHORT
        ).show()
    }
    
    private fun togglePin(note: Note) {
        val updatedNote = note.copy(
            isPinned = !note.isPinned,
            lastModified = System.currentTimeMillis()
        )
        noteViewModel.update(updatedNote)
        Toast.makeText(
            this,
            if (updatedNote.isPinned) "Note pinned 📌" else "Note unpinned",
            Toast.LENGTH_SHORT
        ).show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                showDeleteAllDialog()
                true
            }
            R.id.sort_by_date -> {
                sortNotesByDate()
                true
            }
            R.id.sort_by_title -> {
                sortNotesByTitle()
                true
            }
            R.id.sort_by_color -> {
                sortNotesByColor()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun sortNotesByDate() {
        val currentList = noteAdapter.currentList.sortedByDescending { it.timestamp }
        noteAdapter.submitList(currentList)
        Toast.makeText(this, "Sorted by date", Toast.LENGTH_SHORT).show()
    }
    
    private fun sortNotesByTitle() {
        val currentList = noteAdapter.currentList.sortedBy { it.title.lowercase() }
        noteAdapter.submitList(currentList)
        Toast.makeText(this, "Sorted by title", Toast.LENGTH_SHORT).show()
    }
    
    private fun sortNotesByColor() {
        val currentList = noteAdapter.currentList.sortedBy { it.color }
        noteAdapter.submitList(currentList)
        Toast.makeText(this, "Sorted by color", Toast.LENGTH_SHORT).show()
    }
    
    private fun showNoteOptionsDialog(note: Note) {
        val options = mutableListOf<String>()
        options.add(if (note.isPinned) "📌 Unpin" else "📌 Pin")
        options.add(if (note.isFavorite) "⭐ Remove from Favorites" else "⭐ Add to Favorites")
        options.add("🗑️ Delete")
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Note Options")
            .setItems(options.toTypedArray()) { _, which ->
                when (which) {
                    0 -> {
                        val updatedNote = note.copy(
                            isPinned = !note.isPinned,
                            lastModified = System.currentTimeMillis()
                        )
                        noteViewModel.update(updatedNote)
                    }
                    1 -> {
                        val updatedNote = note.copy(
                            isFavorite = !note.isFavorite,
                            lastModified = System.currentTimeMillis()
                        )
                        noteViewModel.update(updatedNote)
                    }
                    2 -> {
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Delete Note")
                            .setMessage("Are you sure you want to delete this note?")
                            .setPositiveButton("Delete") { _, _ ->
                                noteViewModel.delete(note)
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }
            }
            .show()
    }
    
    private fun showDeleteAllDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete All Notes")
            .setMessage("Are you sure you want to delete all notes? This cannot be undone.")
            .setPositiveButton("Delete All") { _, _ ->
                noteViewModel.deleteAllNotes()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = noteAdapter.getNoteAt(position)
                deleteNoteWithUndo(note)
            }
        })
        
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
    
    private fun deleteNoteWithUndo(note: Note) {
        noteViewModel.delete(note)
        
        Snackbar.make(binding.root, "Note deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                noteViewModel.insert(note)
                Toast.makeText(this, "Note restored", Toast.LENGTH_SHORT).show()
            }
            .setAnchorView(binding.fabAddNote)
            .show()
    }
    
    private fun setupDashboardAnimations() {
        // Animate dashboard cards on load
        val cards = listOf(
            binding.totalNotesCard,
            binding.pinnedNotesCard,
            binding.favoriteNotesCard
        )
        
        cards.forEachIndexed { index, card ->
            card.alpha = 0f
            card.translationY = 50f
            card.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setStartDelay((index * 100).toLong())
                .start()
            
            // Add click animation for cards
            card.setOnClickListener {
                it.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start()
                    }
                    .start()
            }
        }
    }
}
