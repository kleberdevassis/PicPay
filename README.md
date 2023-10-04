
# Projeto de Transferência de Dinheiro - PicPay Simplificado

Bem-vindo ao meu projeto de transferência de dinheiro, inspirado no PicPay Simplificado!

## Sobre

Este projeto foi desenvolvido para apresentar minhas habilidades em desenvolvimento de software. Ele se baseia no desafio proposto por uma empresa de pagamento e visa criar uma aplicação que permite a transferência de dinheiro entre usuários e lojistas.

## Funcionalidades Principais

- Cadastro de usuários com Nome Completo, CPF, E-mail e Senha.
- Garantia de que CPF/CNPJ e e-mails sejam únicos no sistema.
- Usuários podem enviar dinheiro (efetuar transferências) para lojistas e entre usuários.
- Lojistas só recebem transferências, não enviam dinheiro.
- Validação do saldo do usuário antes da transferência.
- Consulta a um serviço autorizador externo antes de finalizar a transferência.
- Transações são tratadas como transações, revertendo em caso de inconsistências.
- Envio de notificações (e-mail, sms) para o usuário ou lojista no recebimento de pagamento, considerando a possibilidade de indisponibilidade do serviço de notificação.

## Tecnologias Utilizadas

- Java 17 com Spring Boot
- Banco de dados (H2/jpa)
- Serviços RESTFul

## Como Executar

1. Clone este repositório para sua máquina local.
2. Configure o ambiente de desenvolvimento, incluindo a configuração do banco de dados.
3. Execute o projeto localmente.
4. Acesse a API via localhost em sua máquina.

## Contribuições

Este é um projeto de código aberto, e ficaria muito feliz em receber contribuições da comunidade. Se você gostaria de contribuir, sinta-se à vontade para abrir um problema, enviar solicitações pull ou entrar em contato comigo.

## Contato

- [Meu Perfil no LinkedIn](linkedin.com/in/kleber-assis-8b00741b7)
- E-mail: kleberdevassis@gmail.com

## Agradecimento
Obrigado por visitar meu projeto e conferir meu trabalho! Fique à vontade para explorar o código-fonte, testar as funcionalidades e entrar em contato se tiver alguma pergunta ou sugestão. Espero que este projeto seja útil para você.
