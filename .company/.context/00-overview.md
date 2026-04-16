# Source of Truth Overview

## What is SoT
- Canonical documents: specs, ADRs, contracts (OpenAPI/events), runbooks.
- The latest approved truth lives here and is versioned by Git.

## Governance
- Only Lead can merge into main (single-writer).
- Others produce Proposed Patches.

## Contract Versioning
- OpenAPI & event schema must be versioned when breaking changes occur.
- Prefer backward compatible changes; if breaking, document migration plan + ADR.

## Working Loop
Task -> BA Spec Patch -> Architect ADR/Contracts Patch -> Lead Merge + Freeze -> Code PR -> Review -> Done

## Status Tags
- draft: not approved
- approved: canonical
- deprecated: kept for history, not for implementation
