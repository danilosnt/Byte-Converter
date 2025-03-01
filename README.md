## How to run the project on your machine

1º - Install and configure PostgreSQL <br>
2º - Create a database named ```byte_to_mb``` <br>
3º - Create the table *users* with this command: 
```sql
CREATE TABLE users {
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL );
```
4º - Create the table *value* with this command:
```sql
CREATE TABLE value (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  byte_value BIGINT NOT NULL,
  FOREING KEY (user_id) REFERENCES users (id) ON DELETE CASCADE );
```
5º - Clone the repository <br>
6º - In ```DatabaseConnection.java``` enter your username and password <br>
7º - Run ```Main.java```
