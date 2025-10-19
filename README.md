# Sistema de Gestão de Ferramentas com Autenticação de Usuário

Este projeto é uma aplicação web completa desenvolvida com **Spring Boot (Java)**, **Thymeleaf** e **Spring Security**, que permite o **cadastro, listagem, edição e exclusão de ferramentas**, com autenticação de usuários e associação entre usuários e as ferramentas que criam.

Um admin pode criar, ver, editar e excluir todas as ferramentas.

Um usuário comum pode criar, ver, editar e excluir apenas as ferramentas que ele criou.

O design da interface foi construído com **HTML5**, **CSS3** e um estilo moderno e leve, inspirado em **painéis administrativos minimalistas**, garantindo boa usabilidade e performance.

---

## Integrantes
Vitor Hugo da Silva - RM558961

---

## Deploy

[https://javacp5-pt2.onrender.com/login](https://javacp5-pt2.onrender.com/login)

---

## Tecnologias Utilizadas

### Back-end
- **Java 21**
- **Spring Boot 3**
- **Spring MVC**
- **Spring Data JPA**
- **Spring Security**
- **Hibernate**
- **Thymeleaf**
- **PostgreSQL**

### Front-end
- **HTML5 / CSS3 (responsivo e otimizado)**
- **Bootstrap-like design (sem dependência externa)**
- **Thymeleaf Templates**

---

## Autenticação e Usuário Logado

O sistema usa o **Spring Security** para autenticação baseada em login e senha.  
Cada usuário cadastrado pode **criar, editar e excluir** suas ferramentas, e todas as ferramentas são associadas ao criador.

A função `getUsuarioLogado()` é responsável por recuperar o usuário autenticado e associá-lo automaticamente ao registro de uma ferramenta.

---

## Prints do Projeto

Tela inicial

<img width="1917" height="942" alt="image" src="https://github.com/user-attachments/assets/b4d596f9-d096-4f4e-8bb0-f4064c0d8134" />

Criando admin

<img width="1919" height="938" alt="image" src="https://github.com/user-attachments/assets/5d921e33-beef-46fe-b3eb-ff5eceb3a962" />

Logando com admin

<img width="1919" height="939" alt="image" src="https://github.com/user-attachments/assets/8b9c609e-faea-4297-b294-e1e025284ff1" />

Tela inicial

<img width="1919" height="919" alt="image" src="https://github.com/user-attachments/assets/d508b1d9-ae21-4fda-846f-4ae35af3fcba" />

Criando ferramenta como admin

<img width="1745" height="679" alt="image" src="https://github.com/user-attachments/assets/9efaa54a-879c-4d46-b6ec-4318e1d46d5d" />

<img width="1914" height="734" alt="image" src="https://github.com/user-attachments/assets/74e26114-4d63-4c59-8d04-faedda339010" />

Criando usuario normal

<img width="1906" height="933" alt="image" src="https://github.com/user-attachments/assets/ebdbc781-c484-4be2-9064-dfc9b9301221" />

Logando com usuario normal

<img width="1875" height="879" alt="image" src="https://github.com/user-attachments/assets/d43c7c49-710f-4853-8153-288e8c194bde" />

Tela inicial (Não aparece a ferramenta do Admin)

<img width="1894" height="926" alt="image" src="https://github.com/user-attachments/assets/f2b926cc-8e63-4eb8-af90-48f0dca86ab5" />

Criando duas ferramentas

<img width="1890" height="826" alt="image" src="https://github.com/user-attachments/assets/70dce351-7706-4a8b-b96f-a6d98e3ae262" />

Editando uma

<img width="1662" height="680" alt="image" src="https://github.com/user-attachments/assets/278c8432-f8fc-4278-b3a8-4a4d602850e0" />

<img width="1416" height="541" alt="image" src="https://github.com/user-attachments/assets/81492398-3ee9-43e6-8249-46123aebd926" />

Excluindo 

<img width="1425" height="523" alt="image" src="https://github.com/user-attachments/assets/3758873e-ea8c-4f0a-85fd-3942ef7c1f69" />

Admin consegue ver todas as ferramentas

<img width="1392" height="577" alt="image" src="https://github.com/user-attachments/assets/2af9d9eb-c60b-4039-a613-1eeb5b4607b2" />

