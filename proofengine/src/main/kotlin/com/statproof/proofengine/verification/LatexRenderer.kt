package com.statproof.proofengine.verification

import com.statproof.proofengine.ast.Expression

object LatexRenderer {
    fun render(expression: Expression): String = when (expression) {
        is Expression.Constant -> expression.value.toString()
        is Expression.Variable -> expression.name
        is Expression.Add -> "${render(expression.left)} + ${render(expression.right)}"
        is Expression.Multiply -> "${render(expression.left)} \\cdot ${render(expression.right)}"
        is Expression.Fraction -> "\\frac{${render(expression.numerator)}}{${render(expression.denominator)}}"
        is Expression.Power -> "${render(expression.base)}^{${render(expression.exponent)}}"
        is Expression.Log -> "\\log\\left(${render(expression.value)}\\right)"
        is Expression.Summation -> "\\sum_{${expression.index}=${render(expression.from)}}^{${render(expression.to)}} ${render(expression.body)}"
        is Expression.Matrix -> "\\begin{bmatrix}" + expression.rows.joinToString(" \\\\ ") { row ->
            row.joinToString(" & ") { render(it) }
        } + "\\end{bmatrix}"
    }
}
