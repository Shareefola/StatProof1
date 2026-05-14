package com.statproof.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Proof(
    val id: String,
    val title: String,
    val topic: Topic,
    val difficulty: Difficulty,
    val metadata: ProofMetadata,
    val assumptions: List<String>,
    val steps: List<ProofStep>
)

@Serializable
data class ProofStep(
    val id: String,
    val expressionState: ExpressionState,
    val transformationRule: TransformationRule,
    val explanation: String,
    val latex: String,
    val substeps: List<ProofStep> = emptyList()
)

@Serializable
data class ExpressionState(
    val before: String,
    val after: String
)

@Serializable
data class TransformationRule(
    val id: String,
    val name: String,
    val description: String
)

@Serializable
data class ProofMetadata(
    val references: List<String>,
    val tags: List<String>,
    val relatedConcepts: List<String>
)

@Serializable
enum class Topic {
    ProbabilityTheory,
    DistributionTheory,
    StatisticalInference,
    HypothesisTesting,
    RegressionModels,
    BayesianStatistics,
    SamplingTheory,
    StochasticProcesses,
    InformationTheory,
    Asymptotics
}

@Serializable
enum class Difficulty {
    Beginner,
    Intermediate,
    Advanced
}

@Serializable
data class VerificationResult(
    val symbolicallyValid: Boolean,
    val numericallyValid: Boolean,
    val details: String
)
