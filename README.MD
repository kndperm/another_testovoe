Приложение изначально работает с базой данных Postgresql.

Для того чтобы приложение работало с базой данных надо указать в файле application.properties путь к базе данных 
(spring.datasource.url), имя пользователя (spring.datasource.username) и пароль от базы данных(spring.datasource.password).
Сами таблицы создаётся самостоятельно при первом запуске приложения. Создаётся схема Test и две таблицы продуктов (Products)
и транзакций (Product_Transactions).

Взаимодействия с приложением происходит через REST запросы.

### Product

_**/product/add**_ - добавляет продукт в базу данных. Параметры передаются через json. Вводные параметры:

name - название продукта

shortName - короткое обозначение

specification - спецификации продукта

_**/product/get/{id}**_ - возвращает данные о продукте через идентификатор продукта (id).

_**/product/edit**_ - изменяет данные о продукте. Параметры передаются через json. Параметры:

id - идентификатор продукта

name - название продукта

shortName - короткое обозначение

specification - спецификации продукта

_**/product/delete/{id}**_ - удаляет продукт через идентификатор (id).

### Transaction

_**/transaction/add_arrival**_ - добавляет поступительную транзакцию. Параметры передаются через json. Параметры:

productId - идентификатор продукта

data - дата транзакции

quantity - количество продукта

**_/transaction/add_allowance_** - добавляет убывающая транзакцию. Параметры передаются через json. 
Параметры такие же что и для постутительной транзакции.

**_/transaction/get/{id}_** - возвращает данные о транзакции через его идентификаоров (id)

**_/transaction/edit_** - изменяет данные о транзакции. Параметры передаются через json. Параметры:

transactionId - идентификатор транзакции

productId - идентификатор продукта

data - дата транзакции

quantity - количество продукта

**_/transaction/delete/{id}_** - удаляет транзакцию через его идентификатор (id)

**_/transaction/product/{id}_** - выводит все транзакции одного продукта. id - идентификатор продукта

**_/transaction/current_product_quantity_** - выводит текущее количество всех продуктов