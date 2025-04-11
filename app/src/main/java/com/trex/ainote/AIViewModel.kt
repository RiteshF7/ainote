package com.trex.ainote

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AIViewModel(application: Application) : AndroidViewModel(application) {
    private val tfLiteHelper = TensorFlowLiteHelper(application)
    private val noteEmbeddings = mutableListOf<FloatArray>()

    fun trainModel(note: String) {
        val embedding = tfLiteHelper.generateEmbedding(note)
        noteEmbeddings.add(embedding)
    }

    fun queryModel(query: String): String {
        val queryEmbedding = tfLiteHelper.generateEmbedding(query)
        val (bestIndex, similarity) = tfLiteHelper.findMostRelevant(noteEmbeddings, queryEmbedding)
        return if (bestIndex != -1) {
            "Best match found with similarity $similarity at index: $bestIndex"
        } else {
            "No relevant match found."
        }
    }
}