## How to run the project on your machine

1º - Install and configure PostgreSQL <br>
2º - Create a database named ```byte_to_mb``` <br>
3º - Clone the repository <br>
4º - In ```DatabaseConnection.java``` enter your username and password <br>
5º - Run ```Main.java```

##  <img align="center" alt="Imagem Java" height="35" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"> Code Structure 

<h3>Libraries</h3>
<h5>Main.java</h5>

* ```java.util.ArrayList``` = Automatically stores a list of values
* ```java.util.Scanner``` = Read what is typed in the console

<h5>Login.java</h5>

* ```java.sql.Connection``` = Establishes a connection to the database
* ```java.sql.PreparedStatement``` = Prepares SQL queries securely and efficiently
* ```java.sql.ResultSet``` = Stores the results of a database query 
* ```java.sql.SQLException``` = Represents errors related to database operations

<h5>DatabaseConnection</h5>

* ```java.sql.Connection``` = Establishes a connection to the database
* ```java.sql.DriverManager``` = Sets up and create connections to the database
* ```java.sql.SQLException``` = Represents errors related to database operations

<h5>ValueManager.java</h5>

* ```java.sql.*```
* ```java.util.ArrayList```

<h5>ValueConverter.java</h5>

* ```java.math.BigDecimal```
* ```java.math.RoudingMode```

<h5>Value</h5>

* No libraries used
