package com.statproof.proofengine.engine

import com.statproof.proofengine.ast.Expression

object EquivalenceChecker {
    fun equivalent(left: Expression, right: Expression): Boolean {
        val l = SimplificationEngine.simplify(left)
        val r = SimplificationEngine.simplify(right)
        return l == r
    }
}
