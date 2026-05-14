package com.statproof.proofengine.engine

import com.statproof.proofengine.ast.Expression
import com.statproof.proofengine.rules.RuleRegistry

object SimplificationEngine {
    fun simplify(expression: Expression): Expression {
        val normalized = simplifyChildren(expression)
        return RuleRegistry.rules.fold(normalized) { current, rule -> rule(current) }
    }

    private fun simplifyChildren(expression: Expression): Expression = when (expression) {
        is Expression.Add -> Expression.Add(simplify(expression.left), simplify(expression.right))
        is Expression.Multiply -> Expression.Multiply(simplify(expression.left), simplify(expression.right))
        is Expression.Fraction -> Expression.Fraction(simplify(expression.numerator), simplify(expression.denominator))
        is Expression.Power -> Expression.Power(simplify(expression.base), simplify(expression.exponent))
        is Expression.Log -> Expression.Log(simplify(expression.value))
        is Expression.Summation -> expression.copy(
            from = simplify(expression.from),
            to = simplify(expression.to),
            body = simplify(expression.body)
        )

        is Expression.Matrix -> expression.copy(rows = expression.rows.map { row -> row.map(::simplify) })
        is Expression.Constant,
        is Expression.Variable -> expression
    }
}
