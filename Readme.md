# Hoaxify App
Hoaxify is a social media app that allows users to share hoaxes. It is a full-stack application that uses Spring Boot for the backend and React for the frontend. It is a project that I created while learning Spring Boot and React.

## Features
- User registration
- User login
- User profile
- User List with pagination
- User profile image upload
- Hoax submission
- Hoax List with load more

## Technologies
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Validation
- Tika-Core
- Spring Boot DevTools
- Lombok
- H2 Database


## Screenshots

<hr></hr>

### SignUp 
<hr></hr>

<strong>POST - /api/v1/users</strong> User registration not-null validation
<img src="./Docs/sign1.png">

 <strong>POST - /api/v1/users</strong> User registration  validation
<img src="./Docs/sign2.png">

<strong>POST - /api/v1/users</strong>  User registration  Success
<img src="./Docs/sign3.png">
<hr></hr>

### Login 
<hr></hr>

<strong>POST - /api/v1/auth</strong> User login authentication
<img src="./Docs/log1.png">

<strong>POST - /api/v1/auth</strong> User login authentication success
<img src="./Docs/log2.png">
<hr></hr>

### User 
<hr></hr>

<strong>GET - /api/v1/users</strong> User List with pagination
<img src="./Docs/user1.png">

<strong>GET - /api/v1/user</strong> User profile 
<img src="./Docs/user2.png">

<strong>GET - /api/v1/user</strong> User profile not found
<img src="./Docs/user3.png">

<strong>PUT - /api/v1/user/userid</strong> User update failure
<img src="./Docs/user4.png">

<strong>PUT - /api/v1/user/userid</strong> User update success
<img src="./Docs/user5.png">
<hr></hr>

### Hoax
<hr></hr>


<strong>GET - /api/v1/hoaxes</strong> Hoax List with pagination
<img src="./Docs/hoax1.png">

<strong>POST - /api/v1/hoaxes</strong> Hoax submission failure
<img src="./Docs/hoax2.png">

<strong>POST - /api/v1/hoaxes</strong> Hoax submission success
<img src="./Docs/hoax3.png">


