-- Create the database and connect to it.
connect 'jdbc:derby://localhost:1527/userauth;create=true';

-- Add a user to the database, username admin, password admin
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user.admin','admin');

-- Grant all privileges to user admin
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.fullAccessUsers','admin');

-- Disconnect from the newly created database
disconnect;

-- Reconnect to the newly created database as user admin
connect 'jdbc:derby://localhost:1527/userauth;user=admin;password=admin';

CREATE TABLE USERS
(
   USER_ID INTEGER PRIMARY KEY not null,
   USERNAME VARCHAR(10) not null,
   FIRST_NAME VARCHAR(15),
   MIDDLE_NAME VARCHAR(15),
   LAST_NAME VARCHAR(20),
   PASSWORD CHAR(160) not null
);

CREATE UNIQUE INDEX SQL070522071825971 ON USERS(USERNAME);


CREATE TABLE GROUPS
(
   GROUP_ID INTEGER PRIMARY KEY not null,
   GROUP_NAME VARCHAR(20) unique not null,
   GROUP_DESC VARCHAR(200)
);

CREATE TABLE USER_GROUPS
(
   USER_ID INTEGER not null,
   GROUP_ID INTEGER not null,
   PRIMARY KEY (USER_ID,GROUP_ID)
);

ALTER TABLE USER_GROUPS
ADD CONSTRAINT SQL070522071838550
FOREIGN KEY (USER_ID)
REFERENCES USERS(USER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE USER_GROUPS
ADD CONSTRAINT SQL070522071838560
FOREIGN KEY (GROUP_ID)
REFERENCES GROUPS(GROUP_ID) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,GROUP_DESC) VALUES (1,'admin','Administrators');
INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,GROUP_DESC) VALUES (2,'user','Regular users');
