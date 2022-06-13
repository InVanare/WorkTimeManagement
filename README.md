# WorkTimeManagment
Work Time Management REST API. 
## Table of contents
* [General info](#general-info)
* [Security](#security)
* [Database](#database)
* [Command](#command)
* [Technologies](#technologies)
* [Inspiration](#inspiration)
* [Copyright](#copyright)
## General info
The task of this project is to prepare the REST API interface to support the working time of employees in the company. With this solution, you can easily check whether the employee came to work and when he started work. I used the REST API interface, which complies with the REST design principles, i.e. the representational state transfer architecture style. The main application engine is Spring Boot. I used Spring Security together with JWT (JSON Web Token) as a security measure. I use the MySQL database as the data source. I used HIBERNATE to manage the database. The POSTMAN program was used to test the API.
## Security
Spring Security was used in this project. Access to all resources has been blocked except login and user registration. JWT (JSON Web Token) technology was used, which is used to identify logged in users. Authentication and authorization are done using filters. Administrator and user rights have been entered.
## Database
This project uses the MySQL database. Support for handling was Hibernate and Spring Data.
## Command
| Command | Description | Auth  | HTTP Protocol | Require key |
|  :---:  |    :---:    | :---: |     :---:     |    :---:    |
| /login  | Logging in        | NO    |      GET      | username <br> password | 
| /api/user/register | Registration | NO | POST | name* <br> pass** <br> mail |
| /api/user/get/id/{id} | Get user data by id (only ADMIN) | YES | GET | - |
| /api/user/get/username/{username} | Get user data by name (only own) | YES | GET | - |
| /api/user/get/my | Get own user data  | YES | GET | - |
| /api/user/update/password | Update password | YES | PUT | pass** |
| /api/user/update/mail | Update email | YES | PUT | mail |
| /api/user/update/active | Update account deactivation | YES | PUT | active*** |
|  ---  |    ---    | --- |     ---     |    ---    |
| /api/organization/create | Create organization | YES | POST | name* |
| /api/organization/get/id/{id} | Get organization data by id (only ADMIN) | YES | GET | - |
| /api/organization/get/name/{name} | Get organization data by name (only own) | YES | GET | - |
| /api/organization/get/my | Get current organization data  | YES | GET | - |
| /api/organization/add/user/{name} | Add user to organization | YES | GET | - |
| /api/organization/remove/user/{name} | Remove user from organization| YES | GET | - |
| /api/organization/update | Update organization | YES | PUT | name* <br> active*** |
|  ---  |    ---    | --- |     ---     |    ---    |
| /api/team/create | Registration | YES | POST | name* |
| /api/team/get/id/{id} | Get team data by id (only ADMIN) | YES | GET | - |
| /api/team/get/name/{name} | Get team data by name (only own) | YES | GET | - |
| /api/team/get/my | Get teams data  | YES | GET | - |
| /api/team/add/user/{username}/team/{teamname} | Add user to team | YES | GET | - |
| /api/team/remove/user/{username}/team/{teamname} | Remove user from team| YES | GET | - |
| /api/team/update | Update team | YES | PUT | nameTeam* <br> nameToUpdate* <br> active*** |
|  ---  |    ---    | --- |     ---     |    ---    |
| /api/presence/get/id/{id} | Get presence data by id (only ADMIN) | YES | GET | - |
| /api/presence/get/user/{name} | Get  presence list by name | YES | GET | - |
| /api/presence/start | Begin own presence | YES | GET | - |
| /api/presence/end | End own presence | YES | GET | - |

\* \- must consist of: 
- [a-z], [A-Z], [1-9]
- min. 1 letter
- min. 5 characters
- max. 20 characters

\** \- must consist of: 
- [a-z], [A-Z], [1-9], [@$!%*?&]
- min. 1 uppercase letter
- min. 1 lowercase letter
- min. 1 lowercase digit
- min. 1 special character
- min. 5 characters
- max. 20 characters

\*** \- only true or false
## Technologies
Project is created with:
* Java version: 18
* Spring Boot: 2.6.6
* Spring Web
* Spring Data JPA
* Spring Security
* MySQL
* HIBERNATE
* JAXB API: 2.3.1
* JAXB Core: 4.0.0-M4
* JavaBeans(TM) Activation: 1.1.1
## Inspiration
The application was created to pass a course at the CodersLab programming school.
## Copyright
Copyright © 2022 InVanare. MIT License. See LICENSE for details.
