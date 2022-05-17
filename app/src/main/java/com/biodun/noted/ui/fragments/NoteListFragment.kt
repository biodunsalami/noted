package com.biodun.noted.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.biodun.noted.NoteAdapter
import com.biodun.noted.NoteInterface
import com.biodun.noted.R
import com.biodun.noted.Repository
import com.biodun.noted.data.Note
import com.biodun.noted.databinding.FragmentNoteListBinding
import com.biodun.noted.ui.MainActivity
import com.biodun.noted.ui.MainActivity.Companion.counter

class NoteListFragment : Fragment(), NoteInterface {

//    lateinit var binding: FragmentNoteListBinding

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteAdapter: NoteAdapter

    private lateinit var notesList: MutableList<Note>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        notesList = Repository().getNotes()

        setUpAdapter(notesList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Hide text
        if(counter >= 0){
            binding.text1.visibility = View.INVISIBLE
        }

        binding.addFab.setOnClickListener {
            MainActivity.isNewNote = true
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

    }

    private fun setUpAdapter(courses: List<Note>) = binding.recyclerView.apply {
        noteAdapter = NoteAdapter(courses, this@NoteListFragment)
        adapter = noteAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onCardClicked(position: Int) {
        Toast.makeText(context, "This Works", Toast.LENGTH_SHORT).show()

        MainActivity.isNewNote = false

        val note = notesList[position]


        findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(note))

    }

}