# Proof Engine Architecture

`proofengine` provides:
- Expression AST
- Parser
- Rule registry
- Simplification
- Equivalence checking
- Verification summary

Pipeline: parse -> simplify -> rewrite -> verify.
