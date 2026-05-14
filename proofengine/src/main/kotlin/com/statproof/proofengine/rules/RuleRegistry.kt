package com.statproof.proofengine.rules

import com.statproof.proofengine.ast.Expression

object RuleRegistry {
    val rules: List<(Expression) -> Expression> = listOf(
        ::reduceIdentityAdd,
        ::reduceIdentityMultiply,
        ::simplifyFraction,
        ::logIdentity
    )

    private fun reduceIdentityAdd(expression: Expression): Expression = when (expression) {
        is Expression.Add -> when {
            expression.left == Expression.Constant(0.0) -> expression.right
            expression.right == Expression.Constant(0.0) -> expression.left
            else -> expression
        }

        else -> expression
    }

    private fun reduceIdentityMultiply(expression: Expression): Expression = when (expression) {
        is Expression.Multiply -> when {
            expression.left == Expression.Constant(1.0) -> expression.right
            expression.right == Expression.Constant(1.0) -> expression.left
            expression.left == Expression.Constant(0.0) || expression.right == Expression.Constant(0.0) -> Expression.Constant(0.0)
            else -> expression
        }

        else -> expression
    }

    private fun simplifyFraction(expression: Expression): Expression = when (expression) {
        is Expression.Fraction -> {
            if (expression.numerator == expression.denominator) Expression.Constant(1.0) else expression
        }

        else -> expression
    }

    private fun logIdentity(expression: Expression): Expression = when (expression) {
        is Expression.Log -> if (expression.value == Expression.Constant(1.0)) Expression.Constant(0.0) else expression
        else -> expression
    }
}
