<h1 align="center" style="font-weight: bold;">DESAFIO BACK-END JUNIOR 💻</h1>

<p align="center">
 <a href="#technologies">Technologies</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
</p>

<p align="center">
    <b>Neste desafio eu desenvolvi uma API RESTful para um sistema de blog , usando Java (Spring) implementando a segurança com JWT e HATEOS.</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- MySQL
- Spring

<h2 id="started">🚀 Getting started</h2>


<h3>Prerequisites</h3>


- MySQL
- Git 2
- Java 21

<h3>Cloning</h3>

```bash
git clone https://github.com/jpfabiano/desafio-back-end-junior.git
```


<h2 id="routes">📍 API Endpoints</h2>

​
| route                                     | description                                          
|-------------------------------------------|--------------------------------
| <kbd>POST /auth/register</kbd>            | Registra um novo usuário - <a href = "#post-auth-register"> Exemplo</a>
| <kbd>POST /auth/login</kbd>               | Loga o usuário, dando um token para ele - <a href = "#post-auth-login"> Exemplo</a>
| <kbd>POST /posts</kbd>                    | Publica um novo Post - <a href = "#post-posts"> Exemplo</a>
| <kbd>GET /posts/desc</kbd>                | Retorna uma lista com todos os Posts em ordem decrescente - <a href = "#get-posts-desc"> Exemplo</a>
| <kbd>GET /posts/cresc</kbd>               | Retorna uma lista com todos os Posts em ordem crescente - <a href = "#get-posts-cresc"> Exemplo</a>
| <kbd>GET /posts/user</kbd>                | Retorna uma lista com todos os Posts do usuário que fez a requisição - <a href = "#get-posts-user"> Exemplo</a>
| <kbd>GET /posts/user/{idPostagem}</kbd>   | Retorna o Post que contem o id fornecido - <a href = "#get-posts-id"> Exemplo</a>
| <kbd>PUT /posts/{idPostagem}</kbd>        | Edita o Post do id fornecido - <a href = "#put-posts-id"> Exemplo</a>
| <kbd>DELETE /posts/{idPostagem}</kbd>     | Deleta o Post do id fornecido - <a href = "#delete-posts-id"> Exemplo</a>


<h3 id="post-auth-register">POST /auth/register</h3>

**REQUEST**
```json
{
    "username" : "jp",
    "login" : "jp@gmail.com",
    "password" : "1234",
    "role" : "ADMIN"
}
```
**RESPONSE**
```json
{
  "userModel": {
        "idUser": "6c4c9c4f-7f90-4190-8e70-6795ab20a8a0",
        "username": "jp",
        "login": "jp@gmail.com",
        "password": "$2a$10$SzRspxyHqx/6/.8Nn4ErYOcAWqFJoPceZPGfDBuq2yDXh8ZcYSiHS",
        "role": "ADMIN",
        "enabled": true,
        "accountNonLocked": true,
        "authorities": [
            {
                "authority": "ROLE_ADMIN"
            },
            {
                "authority": "ROLE_USER"
            }
        ],
        "accountNonExpired": true,
        "credentialsNonExpired": true
    },
    "_links": {
        "Faça seu login": {
            "href": "http://localhost:8080/auth/login"
        }
    }
}
```

<h3 id="post-auth-login">POST /auth/login</h3>

**REQUEST**
```json
{
    "login" : "jp@gmail.com",
    "password" : "1234"
}
```

**RESPONSE**
```json
{
  "token": "OwoMRHsaQwyAgVoc3OXmL1JhMVUYXGGBbCTK0GBgiYitwQwjf0gVoBmkbuyy0pSi"
}
```

OS PRÓXIMOS MÉTODOS PRECISAM DE AUTHENTICAÇÃO

<h3 id="post-posts">POST /post</h3>

**REQUEST**
```json
{
    "texto": "Bom Dia! "
}
```

**RESPONSE**
```json
{
    "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
    "texto": "Bom Dia! ",
    "dataPostagem": "27/02/2024",
    "horaPostagem": "15:44",
    "statusAtualizada": false,
    "usuarioPublicacao": "jp"
}
```

<h3 id="get-posts-desc">GET /post/desc</h3>


**RESPONSE**
```json
{
  "posts": [
        {
            "idPostagem": "5598574e-ffe7-4f80-ac88-7b7e7bd7a07d",
            "texto": "Boa Tarde! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:45",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        },
        {
            "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
            "texto": "Bom Dia! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:44",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        }
    ],
    "_links": {
        "Postagens-crescentes": {
            "href": "http://localhost:8080/posts/cresc"
        },
        "Postagens-jp": {
            "href": "http://localhost:8080/posts/user"
        }
    }
}
```

<h3 id="get-posts-cresc">GET /post/cresc</h3>


**RESPONSE**
```json
 {
    "posts": [
        {
            "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
            "texto": "Bom Dia! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:44",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        },
        {
            "idPostagem": "5598574e-ffe7-4f80-ac88-7b7e7bd7a07d",
            "texto": "Boa Tarde! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:45",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        }
    ],
    "_links": {
        "Postagens-decrescentes": {
            "href": "http://localhost:8080/posts/desc"
        },
        "Postagens-jp": {
            "href": "http://localhost:8080/posts/user"
        }
    }
}
```

<h3 id="get-posts-user">GET /post/user</h3>


**RESPONSE**
```json
 {
    "posts": [
        {
            "idPostagem": "5598574e-ffe7-4f80-ac88-7b7e7bd7a07d",
            "texto": "Boa Tarde! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:45",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        },
        {
            "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
            "texto": "Bom Dia! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:44",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
        }
    ],
    "_links": {
        "Postagens-decrescentes": {
            "href": "http://localhost:8080/posts/desc"
        },
        "Postagens-crescentes": {
            "href": "http://localhost:8080/posts/cresc"
        }
    }
}
```
<h3 id="get-posts-id">Get /posts/{idPostagem}</h3>

**RESPONSE**
```json
{
            "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
            "texto": "Bom Dia! ",
            "dataPostagem": "27/02/2024",
            "horaPostagem": "15:44",
            "statusAtualizada": false,
            "usuarioPublicacao": "jp"
}
```

<h3 id="put-posts-id">PUT /posts/{idPostagem}</h3>

**REQUEST**
```json
{
    "texto": "Boa Noite! "
}
```

**RESPONSE**
```json
{
    "idPostagem": "f12be5ac-d488-43e0-9ef0-a333aacd7c64",
    "texto": "Boa Noite! ",
    "dataPostagem": "27/02/2024",
    "horaPostagem": "15:50",
    "statusAtualizada": true,
    "usuarioPublicacao": "jp"
}
```

<h3 id="delete-posts-id">Delete /posts/{idPostagem}</h3>

ESSE MÉTODO PRECISA DA ROLE "ADMIN"

**RESPONSE**
```json
{
    "Post deletado com sucesso!"
}
```
