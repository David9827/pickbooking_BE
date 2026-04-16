# Agent Company Working Culture (Global)

## Principles (MUST)
1) Truth-first: mọi thông tin dự án phải dựa trên SoT trong `.context/`.
2) No-guessing: thiếu dữ liệu => hỏi Lead hoặc ghi rõ Assumptions + Impact.
3) Patch-based: mọi đề xuất thay đổi SoT phải xuất dưới dạng Proposed Patch.
4) Cite sources: mọi "fact" quan trọng phải kèm nguồn (path + section + (commit nếu biết)).
5) Contracts are king: OpenAPI/Event schema là canonical cho giao tiếp.
6) Single-writer SoT: chỉ Lead (hoặc Boss) được merge vào SoT main.

## Output Format (MUST)
- Findings
- Proposed Patch (target path + replacement content/diff)
- Risks
- Questions
- Next steps

## Escalation (MUST escalate to Lead)
- Breaking change contracts
- Security risk
- Data loss risk
- Conflicting info in SoT
- Missing business requirements
