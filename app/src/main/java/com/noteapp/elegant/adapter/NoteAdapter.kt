package com.noteapp.elegant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noteapp.elegant.R
import com.noteapp.elegant.data.Category
import com.noteapp.elegant.data.Note
import com.noteapp.elegant.databinding.ItemNoteBinding

class NoteAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onNoteLongClick: (Note) -> Unit,
    private val onFavoriteClick: (Note) -> Unit,
    private val onPinClick: (Note) -> Unit,
    private val onDeleteSwipe: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    fun getNoteAt(position: Int): Note = getItem(position)
    
    inner class NoteViewHolder(
        private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(note: Note) {
            binding.apply {
                textTitle.text = note.title
                textContent.text = note.content
                textDate.text = "📅 ${note.getFormattedDate()}"
                textWordCount.text = "📝 ${note.getWordCount()} words"
                
                // Set category chip
                val categoryIcon = Category.getCategoryIcon(note.category)
                categoryChip.text = "$categoryIcon ${note.category}"
                
                // Set pin icon visibility with animation
                if (note.isPinned) {
                    pinIcon.visibility = View.VISIBLE
                    pinIcon.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                } else {
                    pinIcon.visibility = View.GONE
                }
                
                // Set favorite icon visibility with animation
                if (note.isFavorite) {
                    favoriteIcon.visibility = View.VISIBLE
                    favoriteIcon.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                } else {
                    favoriteIcon.visibility = View.GONE
                }
                
                // Set card background color
                val colorResId = when (note.color) {
                    0 -> R.color.note_color_0
                    1 -> R.color.note_color_1
                    2 -> R.color.note_color_2
                    3 -> R.color.note_color_3
                    4 -> R.color.note_color_4
                    5 -> R.color.note_color_5
                    6 -> R.color.note_color_6
                    else -> R.color.note_color_0
                }
                noteCard.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, colorResId)
                )
                
                // Add subtle scale animation on bind
                root.alpha = 0f
                root.scaleX = 0.9f
                root.scaleY = 0.9f
                root.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200)
                    .start()
                
                // Click listeners with ripple effect
                root.setOnClickListener {
                    it.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction {
                        it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                        onNoteClick(note)
                    }.start()
                }
                
                root.setOnLongClickListener {
                    it.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)
                    onNoteLongClick(note)
                    true
                }
                
                // Favorite icon click listener with animation
                favoriteIcon.setOnClickListener {
                    it.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
                    it.animate().scaleX(1.3f).scaleY(1.3f).setDuration(100).withEndAction {
                        it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                    }.start()
                    onFavoriteClick(note)
                }
                
                // Pin icon click listener with animation
                pinIcon.setOnClickListener {
                    it.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
                    it.animate().rotation(360f).setDuration(300).withEndAction {
                        it.rotation = 0f
                    }.start()
                    onPinClick(note)
                }
            }
        }
    }
    
    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
