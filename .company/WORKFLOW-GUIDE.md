# Multi-Agent Workflow Guide

## Overview
Hệ thống multi-agent hoạt động dựa trên **Source of Truth (SoT)** trong `.context/` và tuân theo culture trong `policies/agent-culture.md`.

## Workflow Chính

```
User/Boss → Lead → [BA|Architect|Coder] → Reviewer → Lead → SoT Updated
```

## Agent Communication

### Cách Invoke Agent trong Claude Code

**Option 1: Role-play prompt**
```
Act as the [BA|Architect|Coder|Reviewer|SRE] agent.
Read .company/agent-profiles/[role].md for your responsibilities.
Follow .company/policies/agent-culture.md strictly.

Task: [specific task description]
Context: [relevant info]
```

**Option 2: Dùng System Prompt trong project**
Tạo file `.claude/agent-[role].md` chứa system prompt cho từng agent.

## Output Format (MUST)

Mọi agent MUST output theo format này:

```markdown
## Findings
[What discovered from SoT + analysis]
- Cite: .context/path/file.md#section

## Proposed Patch
Target: .context/path/file.md
Action: create | update | deprecate
Content:
[Full content or diff]

## Risks
- [Risk]: impact + mitigation

## Questions
- [Questions for Lead/User]

## Next steps
- [What should happen next]
```

## Workflow Steps

### 1. Requirements (Lead + BA)
- Lead analyzes user request
- Lead creates task cards
- Lead assigns BA for API/requirements
- BA proposes API contracts (OpenAPI)

### 2. Design (Architect)
- Architect reads BA's contract
- Architect creates ADR for decisions
- Architect proposes architecture updates

### 3. Implementation (Coder)
- Coder reads API contract + ADR
- Coder implements following patterns
- Coder proposes code patches

### 4. Review (Reviewer)
- Reviewer audits security (OWASP Top 10)
- Reviewer validates contract compliance
- Reviewer approves or rejects

### 5. Merge (Lead)
- Lead reviews all patches
- Lead merges to SoT (ONLY Lead can do this)
- Lead moves tasks: backlog → now → done

### 6. Deploy (SRE)
- SRE creates runbooks
- SRE documents deployment
- SRE handles ops

## Escalation Rules

MUST escalate to Lead:
- Breaking changes to contracts
- Security risks
- Data loss risks
- Conflicting info in SoT
- Missing requirements

## Best Practices

1. **Read SoT first** - no guessing
2. **Cite sources** - reference path:line
3. **Use Proposed Patch** - never direct edit SoT
4. **Security first** - OWASP Top 10
5. **Contracts are king** - OpenAPI is canonical
6. **Document decisions** - use ADRs

## Common Workflows

### New Feature
Lead → BA (API) → Architect (Design) → Coder (Impl) → Reviewer → Lead (Merge) → SRE (Deploy)

### Bug Fix
Lead → Coder (Fix) → Reviewer → Lead (Merge)

### Refactoring
Lead → Architect (Design) → Coder (Impl) → Reviewer → Lead
