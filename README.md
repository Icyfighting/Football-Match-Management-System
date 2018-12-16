# Football-Match-Management-System
This project is training project that I completed in programing school.
The project is management system of Football Match.
It provides management of football teams and football match results.
****
    
|Author|Bing Yan|
|---|---
|E-mail|187233718@qq.com


****
## Table of Contents
* [Functions Introduction](#Functions-Introduction)
    * [Team Management](#Team-Management)
    * [Match Management](#Match-Management)
* [Project Directory Structure](#Project-Directory-Structure)
* [How to run](#How-to-run)
    * [Environment Dependence](#Environment-Dependence)
    * [Deployment Configuration](#Deployment-Configuration)
    
### Functions-Introduction
#### Team-Management
```
Team Management module provides adding football team in system with uniqueness verification .
```
#### Match-Management
```
Match Management module provides adding match schedule.
Match schedule includes match date, time, home team, away team, score, competition venue with input validation.
```

### Project-Directory-Structure
* src
    * com\bjsxt
        * biz (main business logic)
        * dao (DAO interface and implements)
        * entity (entity class used in system)
        * test (main method to run)
        * utils (provide util method change resultSet to List)
* oracle_mysql (sql files for MySQL and Oracle, practice purpose)
* match.properties (database connection configuration for MySQL and Oracle, practice purpose)
* README
* LICENSE

### How-to-run
#### Environment-Dependence 
```
JDK 1.8
Oracle
MySQL 5.0
```
#### Deployment-Configuration
```
1. modify database connection configuration in match.properties, choose database type you want.
2. Run corresponding sql file in oracle_mysql to generate tables and initial test data in MySQL database.
3. Run application with main method in com\bjsxt\test
```
