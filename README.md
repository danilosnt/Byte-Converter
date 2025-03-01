## How to run the project on your machine

1º - Install and configure PostgreSQL <br>
2º - Create a database named ```byte_to_mb``` <br>
3º - Create the table ```users``` with this command: 
```sql
CREATE TABLE users {
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL );
```
4º - Create the table ```value``` with this command:
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

## Code structure 

<h3>authenticate</h3>

Authenticate a user by checking the username in the ```users``` table. If the credentials are valid, return the user ```ID```, otherwise, return ```-1```.
```java
public static int authenticate (String username, String password) {
  String query = *SELECT id FROM users WHERE username = ? AND password = ?*

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(query)) {

    ps.setString(1,username);
    ps.setString(2,password);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
      return rs.getInt("id");
    }
  } catch (SQLException e) {
      System.out.println("Error during authentication: " + e.getMessage());
  }

  return -1;
}
```

<h3>registerUser</h3>

Register a new user in the ```users``` table with the provided username and password. Return ```true``` if the insertion is successful, otherwise, return ```false```.

```java
public static boolean registerUser(String username, String password) {
  String insertQuery = "INSERT INTO users (username, password) VALUES (? , ?)";

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(insertQuery)) {

    ps.setString(1, username);
    ps.setString(2, password);
    int result = ps.executeUpdate();

    return result > 0;
  } catch (SQLException e) {
    System.out.println("Error during user registration: " + e.getMessage());
    return false;
  }
}
```

<h3>registerValue</h3>

Register a value in bytes for a ```user``` in the values table. Return ```true``` if the insertion is successful, otherwise, return ```false```.

```java

public static boolean registerValue(long byteValue, int userId) {
  String insertQuery = "INSERT INTO values (byte_value, user_id) VALUES (?, ?)";

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(insertQuery)) {

    ps.setLong(1, byteValue);
    ps.setInt(2, userId);

    int result = ps.executeUpdate();
    return result > 0;
  } catch (SQLException e) {
    System.out.println("Error during value registration: " + e.getMessage());
    return false;
  }
}
```

<h3>listValues</h3>

Retrieve all recorded values for a user from the ```values`` table. Return a list of values associated with the ```userId```.

```java
public static ArrayList<Value> listValues(int userId) {
  ArrayList<Value> values = new ArrayList<>();
  String selectQuery = "SELECT id, byte_value, user_id FROM values WHERE user_id = ?";

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(selectQuery)) {

    ps.setInt(1, userId);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
      values.add(new Value(rs.getInt("id"), rs.getLong("byte_value"), rs.getInt("user_id")));
    }

  } catch (SQLException e) {
    System.out.println("Error during value listing: " + e.getMessage());
  }

  return values;
}
```

<h3>modifyValue</h3>

Modify an existing value in the ```values``` table based on the value ```ID``` and user ```ID```. Return ```true``` if the modification is successful, otherwise, return ```false```.

```java
public static boolean modifyValue(long newByteValue, int valueId, int userId) {
  String updateQuery = "UPDATE values SET byte_value = ? WHERE id = ? AND user_id = ?";

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(updateQuery)) {

    ps.setLong(1, newByteValue);
    ps.setInt(2, valueId);
    ps.setInt(3, userId);

    int result = ps.executeUpdate();
    return result > 0;
  } catch (SQLException e) {
    System.out.println("Error during value modification: " + e.getMessage());
    return false;
  }
}
```

<h3>deleteValue</h3>

Delete a value from the ```values``` table based on the value ```ID``` and user ```ID```. Return ```true``` if the deletion is successful, otherwise, return ```false```.

```java
public static boolean deleteValue(int valueId, int userId) {
  String deleteQuery = "DELETE FROM values WHERE id = ? AND user_id = ?";

  try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(deleteQuery)) {

    ps.setInt(1, valueId);
    ps.setInt(2, userId);

    int result = ps.executeUpdate();
    return result > 0;
  } catch (SQLException e) {
    System.out.println("Error during value deletion: " + e.getMessage());
    return false;
  }
}
```
