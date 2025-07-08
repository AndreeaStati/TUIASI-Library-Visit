# TUIASI visit

## Prerequisites

You should have a mysql server on localhost, using the port 3306.  
For testing purposes you should connect to the database "testing" using the name "adminlib" and no password.
If you want to add the user to the sql server you should run these commands:
```
CREATE USER 'adminlib'@'%' IDENTIFIED WITH mysql_native_password BY '';
GRANT ALL PRIVILEGES ON *.* TO 'adminlib'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

