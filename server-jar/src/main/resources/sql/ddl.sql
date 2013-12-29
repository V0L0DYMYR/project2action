use project2action;
CREATE TABLE USERS
(
  ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
  GOOGLE_ID varchar(2000) NOT NULL,
  email varchar(255),
  FULL_NAME varchar(255),
  locale varchar (255),
  SECURITY_TOKEN varchar (511) NOT NULL
);

CREATE TABLE IDEAS
(
  ID bigint  PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar (511) NOT NULL,
  description varchar (2047),
  author_id bigint  NOT NULL
);


CREATE TABLE PROJECTS
(
  ID bigint  PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name varchar (511) NOT NULL,
  description varchar (2047),
  idea_id bigint  NOT NULL,
  initiator_id bigint  NOT NULL,
  status varchar (255) NOT NULL,
  resolution varchar (2047) NOT NULL,
  start_date bigint  NOT NULL
);

CREATE TABLE USERS_LIKE_IDEAS
(
  IDEA_ID bigint  NOT NULL,
  USER_ID bigint  NOT NULL
);

CREATE TABLE project_participants
(
  project_id bigint  NOT NULL,
  user_id bigint  NOT NULL
);

CREATE TABLE ASSETS
(
  id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT ,
  name varchar (255) NOT NULL,
  description varchar (1023),
  quantity INT NOT NULL,
  unit varchar (2047),
  needed_project_id bigint  NOT NULL,
  provided_by_id bigint  NOT NULL
);