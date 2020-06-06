# Spring-Boot-Security-Role-Based-oAuth2
Role Based Authentication using oAuth2 and JWT

To secure your Restfull web services, there are many ways available, you can secure by using stateless or statefull. Each one has its own pros and cons. In case if you are going to follow microservices architecture and you want to run your services as multiple instances means stateless approach is best one to choose, You can communicate with client side application using token.If you are going  to follow satefull means you need to initiate JSession Id as well as sticky session.

<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/Role-Based-oAuth.gif" width="100%;"/>

This repo will demonstate how to secure your API's based on user Role by using oAuth2 as well as JSON web token.
<br>

Dependecies used for this repo is `spring-security-oauth2` and `spring-security-jwt` <br>
<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/Dependencies.PNG"/>
<br>
 
Used annotation `@Secured` annotation to enable role basis access to each API, ex: `@Secured({ ROLE_ADMIN })`

Enabled Authorization server, Resources server, Web security by using annotation  `@EnableAuthorizationServer`, `@EnableResourceServer`, `@EnableWebSecurity`.
 
You can then access application by using Url here: `http://localhost:8090/`  <br>


## In case you find a bug/suggested improvement for Spring Restfull Webservices
Our issue tracker is available here: [link](https://github.com/Sudarshan-Gowda/Spring-Boot-Rest-Template/issues)


## Working with this project in Spring Tool Suite or Eclipse

### prerequisites
The following items should be installed in your system:
* STS - 3.0 Plus
* MySQL
* Postman or Any Rest API Testing tool

### Steps:

1) Download this Project and do maven import.
```
git clone https://github.com/https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2.git
```
2) To Import the Praject Using STS or Eclipse
```
File -> Import -> Maven -> Existing Maven project
```

## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|Main Controller Class | [UserController](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/tree/master/src/main/java/com/star/sud/user/controller) |
|AuthorizationServerConfig Class | [AuthorizationServerConfig](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/src/main/java/com/star/sud/security/config/AuthorizationServerConfig.java) |
|ResourceServerConfig Class | [ResourceServerConfig](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/src/main/java/com/star/sud/security/config/ResourceServerConfig.java) |
|WebSecurityConfig Class | [WebSecurityConfig](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/src/main/java/com/star/sud/security/config/WebSecurityConfig.java) |
|User Service | [UserServiceImpl](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/src/main/java/com/star/sud/user/service/impl/UserServiceImpl.java) |
|Property File | [application.properties](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/src/main/resources/application.properties) |

## Steps to test the application:

1) Run the Application by using CLI, <br> `mvn spring-boot:run` or build jar file and execute by using below commands.
  `mvn clean install`, `java -jar jar.name`
2) Test the API by using Rest API Testing tool.

<br>

### Find the exposed restfull APIS as below

Place the below urls and select appropicate Http  Methods to test the APIs

a. To generate  the token <br>
http://localhost:8090/oauth/token <br>
<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/pic1.png" width="100%"/>
<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/pic2.png" width="100%"/>

b. To fetch the users by using token<br>
http://localhost:8090/users?access-token=xyz <br>
<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/pic3.png" width="100%"/>

c. In case if you try to access the API with other user which dont have access user will get response as Access denied <br>
http://localhost:8090/users?access-token=xyz <br>
<img src="https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/blob/master/docs/pic4.png" width="100%"/>

   
# Contributing

The [issue tracker](https://github.com/Sudarshan-Gowda/Spring-Boot-Security-Role-Based-oAuth2/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. 

