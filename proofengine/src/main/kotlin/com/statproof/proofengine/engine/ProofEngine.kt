package com.statproof.proofengine.engine

import com.statproof.core.DeterministicJson
import com.statproof.domain.model.Difficulty
import com.statproof.domain.model.ExpressionState
import com.statproof.domain.model.Proof
import com.statproof.domain.model.ProofMetadata
import com.statproof.domain.model.ProofStep
import com.statproof.domain.model.Topic
import com.statproof.domain.model.TransformationRule
import com.statproof.domain.model.VerificationResult
import com.statproof.proofengine.parser.ExpressionParser

class ProofEngine {
    fun parseProof(json: String): Proof = DeterministicJson.codec.decodeFromString(Proof.serializer(), json)

    fun generateSingleStepProof(inputExpression: String): Proof {
        val parsed = ExpressionParser.parse(inputExpression)
        val simplified = SimplificationEngine.simplify(parsed)
        val state = ExpressionState(before = inputExpression, after = simplified.toString())
        val step = ProofStep(
            id = "step-1",
            expressionState = state,
            transformationRule = TransformationRule("simplify", "Simplification", "Canonical simplification"),
            explanation = "Applied deterministic simplification rules.",
            latex = state.after
        )
        return Proof(
            id = "generated-$inputExpression",
            title = "Generated proof for $inputExpression",
            topic = Topic.StatisticalInference,
            difficulty = Difficulty.Beginner,
            metadata = ProofMetadata(emptyList(), listOf("generated"), emptyList()),
            assumptions = emptyList(),
            steps = listOf(step)
        )
    }

    fun verify(before: String, after: String): VerificationResult {
        val left = ExpressionParser.parse(before)
        val right = ExpressionParser.parse(after)
        val symbolic = EquivalenceChecker.equivalent(left, right)
        return VerificationResult(
            symbolicallyValid = symbolic,
            numericallyValid = symbolic,
            details = if (symbolic) "Verified by simplification equivalence." else "Expressions are not equivalent."
        )
    }
}
