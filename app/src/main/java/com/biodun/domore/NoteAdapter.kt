package com.biodun.domore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biodun.domore.data.Note
import com.biodun.domore.databinding.ItemRecyclerBinding

class NoteAdapter(private var notes: List<Note>,
                  private var noteInterface: NoteInterface) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {



    inner class NoteViewHolder(private var binding: ItemRecyclerBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(note: Note){
                binding.noteTitle.text = note.title
                binding.noteText.text = note.noteText



                binding.noteCardView.setOnClickListener {
                    noteInterface.onCardClicked(adapterPosition)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}