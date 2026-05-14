package com.statproof.data

import com.statproof.core.DeterministicJson
import com.statproof.domain.model.Proof
import com.statproof.proofengine.engine.ProofEngine

class ProofRepository(
    private val proofEngine: ProofEngine = ProofEngine()
) {
    fun generateFromInput(input: String): Proof = proofEngine.generateSingleStepProof(input)

    fun parse(json: String): Proof = DeterministicJson.codec.decodeFromString(Proof.serializer(), json)
}
