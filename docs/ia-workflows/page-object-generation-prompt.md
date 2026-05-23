# Page Object Generation Prompt

## Purpose

Generate or maintain the E2E Web test structure based on the provided story and architecture rules.

## Inputs

Read:

- `input/login-story.md`
- `input/test-architecture-rules.md`
- `input/selector-strategy.md`

## Target Project

Create or maintain files under:

`ita-web-automation/src/test/java/com/eduardo/ita`

## Required Packages

- `pages`
- `flows`
- `assertions`
- `tests`

## Architecture Rules

Follow the responsibility separation defined in `test-architecture-rules.md`.

## Page Object Rules

Create one Page Object per screen.

Responsibilities:

- Encapsulate selectors.
- Expose user-intent actions.
- Expose only simple state/read methods needed by assertions.

Rules:

- Selectors must be private.
- Public methods must express user intent.
- Do not put business flow in Page Object.
- Do not put complex assertions in Page Object.
- Do not hardcode environment URLs when config exists.
- Do not use fixed waits.
- Follow `selector-strategy.md`.

## Flow Rules

Create one Flow per business journey when needed.

Responsibilities:

- Combine Page Object actions into user journeys.
- Represent business actions, not selectors.

Rules:

- Flow must not know selectors.
- Flow must not perform assertions.
- Avoid duplicated methods that do the same action with different data.
- Prefer generic actions such as `loginAs(email, password)` instead of `loginWithValidUser()` and `loginWithInvalidUser()`.

## Assertion Rules

Create assertion classes focused on observable outcomes.

Responsibilities:

- Validate expected user-visible results.
- Keep assertions out of tests when reusable.

Rules:

- Assertions must not know selectors directly.
- Assertions should use Page Object state/read methods.
- Split assertions by page or result when the feature grows.

## Test Class Rules

Create test classes that orchestrate scenarios.

Responsibilities:

- Arrange test data.
- Execute flows.
- Call assertions.

Rules:

- Tests must not use selectors.
- Tests must not contain low-level Playwright actions.
- Tests must not duplicate browser setup if a base test exists.
- Prefer `BaseWebTest`, fixture or browser manager for lifecycle.

## Output

Generate or update the required Java classes.

For each generated file, show:

- File path
- Class name
- Responsibility
- Full Java code

## Quality Gates

Before finishing, validate:

- No selector appears in test classes.
- No assertion appears inside Page Object.
- No business journey appears inside Page Object.
- No duplicated flow methods with only data variation.
- Browser lifecycle is isolated when possible.
- Code follows SRP and is easy to maintain.