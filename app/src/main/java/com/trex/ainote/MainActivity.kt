package com.trex.ainote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    private lateinit var aiViewModel: AIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = AIViewModelFactory(application)
        aiViewModel = ViewModelProvider(this, factory).get(AIViewModel::class.java)

        setContent {
            AIAppScreen(viewModel = aiViewModel)

        }
    }
}

@Composable
fun AIAppScreen(viewModel: AIViewModel = viewModel()) {
    var userText by remember { mutableStateOf("") }
    var queryText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = userText,
            onValueChange = { userText = it },
            label = { Text("Enter text to train") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.trainModel(userText) }) {
            Text("Train")
        }
        TextField(
            value = queryText,
            onValueChange = { queryText = it },
            label = { Text("Ask a question") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { result = viewModel.queryModel(queryText) }) {
            Text("Submit Query")
        }
        Text(
            text = "Result: $result",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}