# Test Architecture Rules

## Page Object Rules

### Responsabilidades

- Cada tela deve possuir uma classe Page.
- Page Objects representam elementos e ações da tela.
- Page Objects encapsulam seletores e interações de UI.
- Métodos devem representar intenção do usuário.

### Regras

- Classes Page devem ficar em `src/test/java/com/eduardo/ita/pages`.
- O nome deve seguir o padrão:
    - `LoginPage`
    - `HomePage`
    - `ProductPage`
- Seletores devem ser privados.
- Métodos de ação devem ser públicos.
- Métodos devem expressar intenção, não detalhe técnico.
- Não expor `Locator` diretamente.
- Não utilizar waits fixos (`Thread.sleep`).
- Não colocar regra de negócio dentro da Page.
- Não colocar assertions complexas dentro da Page.

### Bons exemplos

```java
fillEmail()

submitLogin()

isErrorMessageVisible()