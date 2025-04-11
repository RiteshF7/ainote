package com.trex.ainote

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class TensorFlowLiteHelper(context: Context) {

    private val interpreter: Interpreter

    init {
        // Load the TensorFlow Lite model as ByteBuffer
        interpreter = Interpreter(loadModelFile(context, "model.tflite"))
    }

    /**
     * Loads the TensorFlow Lite model file from the assets directory.
     */
    private fun loadModelFile(context: Context, modelFileName: String): ByteBuffer {
        // Open model file in assets
        val fileDescriptor = context.assets.openFd(modelFileName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    /**
     * Generates embeddings for the given input text.
     */
    fun generateEmbedding(inputText: String): FloatArray {
        // Input must match model requirements (you may need to adjust this based on the model)
        val input = arrayOf(inputText)
        // Define output shape based on your model
        val output = Array(1) { FloatArray(512) } // Adjust size if your model's output differs
        interpreter.run(input, output)
        return output[0]
    }

    fun findMostRelevant(
        embeddings: List<FloatArray>,
        queryEmbedding: FloatArray
    ): Pair<Int, Double> {
        var maxSimilarity = -1.0
        var bestMatchIndex = -1

        for (i in embeddings.indices) {
            val similarity = cosineSimilarity(queryEmbedding, embeddings[i])
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity
                bestMatchIndex = i
            }
        }
        return Pair(bestMatchIndex, maxSimilarity)
    }

    private fun cosineSimilarity(vec1: FloatArray, vec2: FloatArray): Double {
        val dotProduct = vec1.zip(vec2).map { it.first * it.second }.sum()
        val magnitude1 = Math.sqrt(vec1.map { it * it }.sum().toDouble())
        val magnitude2 = Math.sqrt(vec2.map { it * it }.sum().toDouble())
        return dotProduct / (magnitude1 * magnitude2)
    }
}