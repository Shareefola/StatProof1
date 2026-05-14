package com.statproof.proofengine.parser

import com.statproof.proofengine.ast.Expression

object ExpressionParser {
    fun parse(input: String): Expression {
        val trimmed = input.replace(" ", "")
        return when {
            trimmed.matches(Regex("^-?\\d+(\\.\\d+)?$")) -> Expression.Constant(trimmed.toDouble())
            "/" in trimmed -> {
                val parts = trimmed.split("/", limit = 2)
                Expression.Fraction(parse(parts[0]), parse(parts[1]))
            }
            "+" in trimmed -> {
                val parts = trimmed.split("+", limit = 2)
                Expression.Add(parse(parts[0]), parse(parts[1]))
            }
            "*" in trimmed -> {
                val parts = trimmed.split("*", limit = 2)
                Expression.Multiply(parse(parts[0]), parse(parts[1]))
            }
            "log(" in trimmed && trimmed.endsWith(")") -> {
                Expression.Log(parse(trimmed.removePrefix("log(").dropLast(1)))
            }
            else -> Expression.Variable(trimmed)
        }
    }
}
