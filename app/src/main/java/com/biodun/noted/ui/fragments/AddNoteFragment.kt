package com.biodun.noted.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.biodun.noted.R
import com.biodun.noted.Repository
import com.biodun.noted.databinding.FragmentAddNoteBinding
import com.biodun.noted.ui.MainActivity
import com.biodun.noted.ui.MainActivity.Companion.counter



class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding


    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        var noteId = 0


        if(MainActivity.isNewNote){
            //clear input fields for new note
            binding.noteTitle.setText("")
            binding.note.setText("")

            counter++

            //Saves new note
            binding.saveFab.setOnClickListener {
                val noteTitle = binding.noteTitle.text.toString()
                val noteText = binding.note.text.toString()


                repository.addNote(counter, noteTitle, noteText)

                findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)

            }

        }else {
            arguments.let {
                val args = AddNoteFragmentArgs.fromBundle(requireArguments())
                binding.noteTitle.setText(args.note.title)
                binding.note.setText(args.note.noteText)
                val noteId = args.note.noteId

                Log.e("MAG", "${args.note}")


                binding.saveFab.setOnClickListener {

                    val noteTitle = binding.noteTitle.text.toString()
                    val noteText = binding.note.text.toString()


                    repository.updateNote(noteId, noteTitle, noteText)

                    findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
                }


            }

        }
    }
}
