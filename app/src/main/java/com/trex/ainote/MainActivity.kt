package com.trex.ainote

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var aiViewModel: AIViewModel
    private lateinit var noteEditText: EditText
    private lateinit var addNoteButton: Button
    private lateinit var notesTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Assuming you have activity_main.xml

        val factory = AIViewModelFactory(application)
        aiViewModel = ViewModelProvider(this, factory)[AIViewModel::class.java]

        noteEditText = findViewById(R.id.noteEditText)
        addNoteButton = findViewById(R.id.addNoteButton)
        notesTextView = findViewById(R.id.notesTextView)

        aiViewModel.notes.observe(this) { notes ->
            notesTextView.text = "Notes:\n" + notes.joinToString("\n")
        }

        addNoteButton.setOnClickListener {
            val noteText = noteEditText.text.toString()
            if (noteText.isNotBlank()) {
                aiViewModel.addNote(noteText)
                noteEditText.text.clear()
            }
        }
    }
}

// You'll also need to create activity_main.xml in the layout directory:
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/noteEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your note" />

    <Button
        android:id="@+id/addNoteButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add Note" />

    <TextView
        android:id="@+id/notesTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Notes:" />
</LinearLayout>
*/