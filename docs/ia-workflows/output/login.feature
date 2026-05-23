Funcionalidade: Login

  Contexto:
    Dado que o usuário está na tela de login

  Cenário: Usuário válido realiza login com sucesso
    Quando informar credenciais válidas
    Então deve acessar a área autenticada

  Cenário: Usuário inválido visualiza mensagem de erro
    Quando informar credenciais inválidas
    Então deve visualizar uma mensagem de erro

  Cenário: Campos obrigatórios são validados
    Quando tentar acessar sem informar credenciais
    Então os campos obrigatórios devem ser validados
