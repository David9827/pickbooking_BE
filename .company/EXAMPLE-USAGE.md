# Quick Start: Using Multi-Agent System

## Example: Add JWT Authentication

### Step 1: User Request
```
User: "Tôi muốn thêm JWT authentication"
```

### Step 2: Invoke Lead Agent
```
Act as Lead agent (see .company/agent-profiles/lead.md).
Follow .company/policies/agent-culture.md.

User request: Add JWT authentication

Tasks:
1. Read current .context/
2. Break down into tasks
3. Assign to agents
```

### Step 3: Lead Creates Tasks
Lead outputs task breakdown:
- T001: BA - Define API contract
- T002: Architect - Design JWT architecture + ADR
- T003: Coder - Implement
- T004: Reviewer - Security audit
- T005: SRE - Deployment runbook

### Step 4: Invoke BA Agent
```
Act as BA agent (see .company/agent-profiles/ba.md).

Task: Define JWT API contract
```

BA outputs:
- API specification (OpenAPI)
- Glossary updates
- Proposed patches to .context/03-apis/

### Step 5: Invoke Architect Agent
```
Act as Architect agent (see .company/agent-profiles/architect.md).

Task: Design JWT architecture
Context: BA's API contract (see above)
```

Architect outputs:
- ADR document
- Architecture diagram
- Proposed patches to .context/05-decisions/

### Step 6: Lead Reviews & Approves
Lead merges approved patches to .context/

### Step 7: Invoke Coder Agent
```
Act as Coder agent (see .company/agent-profiles/coder.md).

Task: Implement JWT service
SoT to read:
- .context/03-apis/auth-api.yaml
- .context/05-decisions/002-jwt-auth.md
```

Coder outputs:
- Code implementation
- Tests
- Proposed code patches

### Step 8: Invoke Reviewer Agent
```
Act as Reviewer agent (see .company/agent-profiles/reviewer.md).

Task: Security review JWT implementation
Code: [Coder's patches]
```

Reviewer outputs:
- Security audit report
- Issues found + fixes
- Approve/Reject recommendation

### Step 9: Fix → Re-review → Approve
If issues found: Coder fixes → Reviewer re-checks → Approve

### Step 10: Lead Merges & Deploys
Lead merges code → SRE creates runbook → Deploy

---

## Quick Commands

### Invoke any agent:
```bash
# Replace [ROLE] with: lead, ba, architect, coder, reviewer, sre
Act as [ROLE] agent (see .company/agent-profiles/[ROLE].md).
Follow .company/policies/agent-culture.md.
Task: [your task]
```

### Check SoT:
```bash
# Read current state of truth
ls -la .company/.context/
```

### See all agents:
```bash
ls .company/agent-profiles/
```

---

## Key Principles

1. **One agent, one responsibility**
2. **Always cite SoT sources**
3. **Use Proposed Patch format**
4. **Security first (Reviewer is critical)**
5. **Only Lead merges to SoT**

For detailed workflow, see `WORKFLOW-GUIDE.md`
