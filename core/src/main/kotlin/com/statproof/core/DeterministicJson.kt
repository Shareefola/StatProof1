package com.statproof.core

import kotlinx.serialization.json.Json

object DeterministicJson {
    val codec: Json = Json {
        prettyPrint = true
        prettyPrintIndent = "  "
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = false
    }
}
