package com.statproof.proofengine

import com.statproof.proofengine.ast.Expression
import com.statproof.proofengine.engine.EquivalenceChecker
import com.statproof.proofengine.engine.ProofEngine
import com.statproof.proofengine.engine.SimplificationEngine
import com.statproof.proofengine.parser.ExpressionParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ProofEngineTest {
    private val engine = ProofEngine()

    @Test
    fun `parser handles fractions`() {
        val expression = ExpressionParser.parse("x/x")
        assertEquals(Expression.Fraction(Expression.Variable("x"), Expression.Variable("x")), expression)
    }

    @Test
    fun `simplifier reduces self fraction`() {
        val expression = ExpressionParser.parse("x/x")
        val simplified = SimplificationEngine.simplify(expression)
        assertEquals(Expression.Constant(1.0), simplified)
    }

    @Test
    fun `equivalence checker compares normalized forms`() {
        assertTrue(EquivalenceChecker.equivalent(ExpressionParser.parse("x/x"), ExpressionParser.parse("1")))
    }

    @Test
    fun `proof engine verify returns deterministic result`() {
        val result = engine.verify("log(1)", "0")
        assertTrue(result.symbolicallyValid)
        assertTrue(result.numericallyValid)
    }
}
