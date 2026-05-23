# Selector Strategy

## Purpose

Define the selector strategy for E2E Web automation.

This file must guide Page Object generation and maintenance.

## Target

Application: ServeRest Front  
Base URL: `https://front.serverest.dev/`  
Framework: Playwright Java

## Selector Priority

Use selectors in this order:

1. `data-testid`
2. `getByRole`
3. `getByLabel`
4. `getByPlaceholder`
5. Stable CSS selector
6. XPath only as a last resort

## Rules

- Prefer selectors that represent user-facing behavior.
- Prefer semantic selectors over structural selectors.
- Keep all selectors private inside Page Objects.
- Do not expose locators directly to tests.
- Do not use brittle selectors based on layout, index, or generated classes.
- Do not use fixed waits to compensate for bad selectors.
- If `data-testid` exists, use it as first option.
- If `data-testid` does not exist, prefer accessibility-based selectors.
- If no stable selector exists, document the limitation and suggest adding `data-testid`.

## Forbidden Selectors

Avoid:

- Absolute XPath
- CSS with dynamic classes
- CSS based on element order, such as `div:nth-child(3)`
- Text selectors for content that changes frequently
- Selectors copied from browser DevTools without review

## Suggested Selectors for Current Story

| Element | Preferred selector | Fallback selector |
| --- | --- | --- |
| Email field | `page.getByPlaceholder("Digite seu email")` | `page.locator("input[name='email']")` |
| Password field | `page.getByPlaceholder("Digite sua senha")` | `page.locator("input[name='password']")` |
| Login button | `page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Entrar"))` | `page.locator("button[type='submit']")` |
| Register link | `page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cadastre-se"))` | `page.locator("a[href*='cadastrar']")` |
| Alert message | `page.getByRole(AriaRole.ALERT)` | `page.locator(".alert, .alert-danger")` |

## Page Object Usage

Selectors must be encapsulated inside Page Objects.

Example:

```java
private Locator emailInput() {
    return page.getByPlaceholder("Digite seu email");
}