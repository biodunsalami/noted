package com.biodun.domore.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.biodun.domore.*
import com.biodun.domore.data.Note
import com.biodun.domore.databinding.FragmentAddNoteBinding
import com.biodun.domore.ui.MainActivity



class AddNoteFragment : Fragment() {

    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory(
            (activity?.application as BaseApplication).noteDatabase.noteDao()
        )
    }

    lateinit var note: Note

    private lateinit var binding: FragmentAddNoteBinding


    private val navigationArgs: AddNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var id = navigationArgs.noteId


        if (MainActivity.isNewNote) {
            //clear input fields for new note
            binding.noteTitle.setText("")
            binding.note.setText("")


//            anyNotePresent = true

            //Saves new note
            binding.saveFab.setOnClickListener {
                saveNote()
            }

        } else {

            viewModel.retrieveNote(id).observe(this.viewLifecycleOwner) { selectedNote ->
                note = selectedNote

                binding.noteTitle.setText(note.noteTitle, TextView.BufferType.SPANNABLE)
                binding.note.setText(note.noteText, TextView.BufferType.SPANNABLE)

                binding.saveFab.setOnClickListener {
                    updateNote()
                }

            }


        }

    }

    private fun isValidEntry(): Boolean {
        return viewModel.isEntryValid(
            binding.noteTitle.text.toString(),
            binding.note.text.toString()
        )
    }


    private fun saveNote(){
        if (isValidEntry()){
            viewModel.addNewNote(
                binding.noteTitle.text.toString(),
                binding.note.text.toString()
            )
            val action = AddNoteFragmentDirections.actionAddNoteFragmentToNoteListFragment()
            findNavController().navigate(action)
        }
    }


    private fun updateNote(){
        if(isValidEntry()){
            viewModel.updateNote(
                this.navigationArgs.noteId,
                this.binding.noteTitle.text.toString(),
                this.binding.note.text.toString()
            )

            val action = AddNoteFragmentDirections.actionAddNoteFragmentToNoteListFragment()
            findNavController().navigate(action)
        }
    }







}

