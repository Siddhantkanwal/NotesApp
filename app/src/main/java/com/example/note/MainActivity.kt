package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.INotesRVAdapter
import com.example.note.NoteViewModel
import com.example.note.NotesRVAdapter
import java.util.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter= NotesRVAdapter(this,this)
        recyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: Note){
        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val input=findViewById<EditText>(R.id.input)
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNode(Note(noteText))
            Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_LONG).show()
            input.getText().clear()
        }
    }
}