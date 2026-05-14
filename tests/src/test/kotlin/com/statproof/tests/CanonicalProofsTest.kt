package com.statproof.tests

import com.statproof.proofengine.engine.ProofEngine
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CanonicalProofsTest {
    private val engine = ProofEngine()

    @Test
    fun `simple deterministic proof verification passes`() {
        val result = engine.verify("x/ x", "1")
        assertTrue(result.symbolicallyValid)
    }
}
