[![Build Status](https://travis-ci.com/Selesito/job4j_rest.svg?branch=master)](https://travis-ci.com/Selesito/job4j_rest)
# job4j_rest

### Описание проекта 
Первый ознакомительные проект с RESTful. Spring boot имеет удобный механизм интеграции Rest сервисов. RestTemplate - позволяет осуществлять вызовы стороннего REST API для взаимодействовия между собой двух Rest сервисов. Это простой сервис, который реализует только серверную часть.

### Использованные технологии

<ul>
  <li>Spring Boot</li>
  <li>Spring Data</li>
  <li>Maven</li>
  <li>PostgresSQL</li>
  <li>liquibase</li>
  <li>Travis C.I.</li>
</ul>

### Методы

<ul>
  <li>Получить всех сотрудников. GET: /employee/</li>
  <li>Получить сотрудникоа по id. GET: /employee/{id}</li>
  <li>Создать сотрудников. POST: /employee/</li>
  <li>Обновить сотрудников. PUT: /employee/</li>
  <li>Удалить сотрудников по id. DELETE: /employee/{id}</li>
  <li>Получение всех аккаунтов сотрудниа. GET: /employee/accounts/{idEml}</li>
  <li>Создать аккаунт сотрудника. POST: /employee/account/{idEml}</li>
  <li>Получить сотрудникоа по id. GET: /employee/{id}</li>
  <li>Обновить аккаунт сотрудника. PUT: /employee/account/</li>
  <li>Получить аккаунт сотрудника по id. GET: /employee/account/{id},{idEml}</li>
  <li>Удалить аккаунт сотрудника по id. DELETE: /employee/account/{id}</li>
</ul>

