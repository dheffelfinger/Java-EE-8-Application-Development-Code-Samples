The create_populate_tables.sql script creates a database, adds some tables to it and prepopulates some of the tables.

To run the script: 

From the command line: 

1. Change directory to [glassfish installation directory]\glassfish\bin
2. run .\asadmin start-database 
3. Change directory to [glassfish installation directory]\javadb\bin.
4. Open the file called setNetworkClientCP.bat in your favorite text editor.
5. Look for a line that looks like this:
   rem set DERBY_INSTALL=
6. Uncomment the above line and add the javadb installation directory to the
right of the equal sign (the value should be [glassfish installation
directory]\glassfish\javadb).
7. From the command line and still in the directory from step 3, execute the above script:
   .\setNetworkClientCP.bat
8. Execute the following command:
   .\ij.bat

   You should now be executing ij, a command line sql interpreter included
with JavaDB.

9. Type the following in ij:
   run '\path\to\script\create_populate_tables.sql';

   The script should now execute, it should create the database, create the
tables and prepopulate some of the tables.

10. Type quit; at the ij prompt.
   You should now be at the command line prompt.
  
   The database and tables should have been created.
   
Note: Before creating the connection pool and datasource the domain should be
running. This can be accomplished by entering the following command:
"asadmin start-domain domain1" at the [glassfish installation
directory]\glassfish\bin directory (assuming we are using the default domain
called domain1).


To create the connection pool:

The connection pool can be created graphically like explained in chapter 1 or by executing the following steps:

1. Change directory to [glassfish installation directory]\glassfish\bin.
2. Execute the following command (without the quotes):
   ".\asadmin create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource --restype javax.sql.DataSource --property DatabaseName=customerdb:User=dev:password=dev CustomerDBPool"

To create the datasource:

The datasource can be created graphically like explained in chapter 1 or by executing the following steps:

1. Change directory to [glassfish installation directory]\glassfish\bin.
2. Execute the following command (without the quotes):
   ".\asadmin create-jdbc-resource --connectionpoolid CustomerDBPool jdbc/__CustomerDBPool"  
   
After following all of the above steps. Examples using database connectivity should deploy and execute properly. 

