CREATE USER mes IDENTIFIED BY 12345;


GRANT CONNECT,RESOURCE,DBA TO mes;
GRANT CREATE TABLE, CREATE VIEW TO mes;
GRANT CONNECT,DBA TO mes;