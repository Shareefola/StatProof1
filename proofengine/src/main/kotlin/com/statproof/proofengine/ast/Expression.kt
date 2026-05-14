package com.statproof.proofengine.ast

sealed interface Expression {
    data class Constant(val value: Double) : Expression
    data class Variable(val name: String) : Expression
    data class Add(val left: Expression, val right: Expression) : Expression
    data class Multiply(val left: Expression, val right: Expression) : Expression
    data class Fraction(val numerator: Expression, val denominator: Expression) : Expression
    data class Power(val base: Expression, val exponent: Expression) : Expression
    data class Log(val value: Expression) : Expression
    data class Summation(val index: String, val from: Expression, val to: Expression, val body: Expression) : Expression
    data class Matrix(val rows: List<List<Expression>>) : Expression
}
