create database coindispenserdb;

create table COIN(
   ID INT NOT NULL AUTO_INCREMENT,
   TYPE DECIMAL (2,2) NOT NULL UNIQUE,
   REMAINING_QUANTITY INT NOT NULL,
   CONFIGURED_QUANTITY INT NOT NULL,
   PRIMARY KEY ( ID )
);

INSERT INTO COIN (TYPE,REMAINING_QUANTITY,CONFIGURED_QUANTITY) VALUES (0.01,100,100);
INSERT INTO COIN (TYPE,REMAINING_QUANTITY,CONFIGURED_QUANTITY) VALUES (0.05,100,100);
INSERT INTO COIN (TYPE,REMAINING_QUANTITY,CONFIGURED_QUANTITY) VALUES (0.10,100,100);
INSERT INTO COIN (TYPE,REMAINING_QUANTITY,CONFIGURED_QUANTITY) VALUES (0.25,100,100);
	
COMMIT;