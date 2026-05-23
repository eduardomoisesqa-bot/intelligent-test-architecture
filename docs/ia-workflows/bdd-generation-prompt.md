# BDD Generation Prompt

## Purpose

Generate business-oriented BDD scenarios based on the provided story file.

## Input

Read the story from:

`input/login-story.md`

## Context

- Target layer: E2E Web
- Language: Portuguese
- Style: Business-readable
- ## Generation Rules

- Generate scenarios focused on user behavior.
- Use business language only.
- Avoid technical implementation details.
- Each scenario must validate a single behavior.
- Reuse common preconditions in Contexto.
- Prefer clear and concise scenario titles.
## Forbidden Rules

Do NOT include:
- CSS selectors
- XPath
- Playwright commands
- Technical waits
- API calls
- Database references
- Assertions implementation
## Output Contract

Generate the output using Gherkin syntax:

```gherkin
Funcionalidade: [feature]

  Contexto:
    Dado [precondition]

  Cenário: [scenario title]
    Quando [action]
    Então [expected result]