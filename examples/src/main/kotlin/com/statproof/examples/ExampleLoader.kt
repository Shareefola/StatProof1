package com.statproof.examples

import java.io.BufferedReader

object ExampleLoader {
    fun loadResource(path: String): String =
        requireNotNull(ExampleLoader::class.java.getResourceAsStream(path)) { "Resource not found: $path" }
            .bufferedReader()
            .use(BufferedReader::readText)
}
