CREATE TABLE USER (
    userid INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    employeeid VARCHAR(100) NOT NULL,
    PRIMARY KEY (userid),
	UNIQUE (employeeid)
);

CREATE TABLE PROJECT (
    projectid INT NOT NULL AUTO_INCREMENT,
    project VARCHAR(100) NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    priority INT NOT NULL,
    userid INT NOT NULL,    
    PRIMARY KEY (projectid),
	FOREIGN KEY (userid) REFERENCES user(userid)
);

CREATE TABLE PARENTTASK (
    parentid INT NOT NULL AUTO_INCREMENT,
    parenttask VARCHAR(100) NOT NULL,
	projectid INT NOT NULL,
    PRIMARY KEY (parentid),
	FOREIGN KEY (projectid) REFERENCES project(projectid)
);

CREATE TABLE TASK (
    taskid INT NOT NULL AUTO_INCREMENT,
    parentid INT NULL,
    projectid INT NOT NULL,
    task VARCHAR(100) NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    priority INT NOT NULL,
    STATUS VARCHAR(100) NOT NULL,
    PRIMARY KEY (taskid),
    FOREIGN KEY (projectid) REFERENCES project(projectid),
	FOREIGN KEY (parentid) REFERENCES parenttask(parentid)
);