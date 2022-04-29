CREATE USER 'bikes'@'localhost' IDENTIFIED BY 'farm_exam';

GRANT ALL PRIVILEGES ON * . * TO 'bikes'@'localhost';


# See the MySQL Reference Manual for details: 
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html

ALTER USER 'bikes'@'localhost' IDENTIFIED WITH mysql_native_password BY 'bikes';