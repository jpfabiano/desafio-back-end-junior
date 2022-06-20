# Desafio Back-end Júnior
Seja bem-vindo! Este desafio foi projetado para avaliar a sua capacidade técnica como candidato.

Você deverá desenvolver um blog. Para isso, queremos que você desenvolva uma API REST utilizando Java com Spring Boot que deve conter as seguintes rotas:

- `/register` - [POST] - esta rota deve cadastrar um usuário;
- `/login` - [POST] - esta rota deve autenticar um usuário;
- `/posts` - [POST] - esta rota deve cadastrar uma postagem mantendo a referência do autor. (requer autenticação);
- `/posts/{id}` - [PUT] - esta rota deve editar a postagem do ID especificado mantendo a referência do autor. (requer autenticação);
- `/posts` - [GET] - esta rota deve retornar a lista de todas as postagens ordenadas das mais recentes para as mais antigas com a possibilidade de inverter esta ordenação e de retornar apenas as postagens do usuário que fez a requisição (requer autenticação);
- `/posts/{id}` - [GET] - esta rota deve retornar a postagem do ID especificado com todos os seus dados  (requer autenticação);
- `/posts/{id}` - [DELETE] - esta rota deve deletar a postagem do ID especificado.

Crie o seu projeto como um repositório público do github e nos envie o link.

**Observações:**
- A API deve retornar e receber os dados em formato JSON
- Você pode utilizar o banco de dados de sua preferência (relacional ou não relacional).
