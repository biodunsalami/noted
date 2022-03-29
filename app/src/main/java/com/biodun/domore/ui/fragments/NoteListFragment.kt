package com.biodun.domore.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.biodun.domore.*
import com.biodun.domore.databinding.FragmentNoteListBinding
import com.biodun.domore.ui.MainActivity

class NoteListFragment : Fragment() {

    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory(
            (activity?.application as BaseApplication).noteDatabase.noteDao()
        )
    }

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteAdapter: NoteListAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        noteAdapter = NoteListAdapter{
            val action = NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(it.id)
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = noteAdapter


        viewModel.allNotes.observe(this.viewLifecycleOwner){notes ->
            notes.let {
                noteAdapter.submitList(it)
            }

            //Hide text
            if(viewModel.allNotes.value?.isNotEmpty() == true){
                binding.text1.visibility = View.INVISIBLE
            }
        }


        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.addFab.setOnClickListener {
            MainActivity.isNewNote = true

            val action = NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(-1)
            findNavController().navigate(action)

        }

    }


}