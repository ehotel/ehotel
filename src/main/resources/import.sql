INSERT INTO `admin` (`benutzername`,`email`,`ist_aktiviert`,`nachname`,`password`,`rechte`,`vorname`) VALUES ('admin',NULL,true,NULL,'21232f297a57a5a743894a0e4a801fc3','ROLE_ADMIN',NULL);
 
INSERT INTO `gast` (`aktivierungs_hash`, `benutzername`, `email`, `ist_aktiviert`, `nachname`, `password`, `rechte`, `vorname`) VALUES ('c6a2df0f8bfe7072fee2eb57573f298b','user','max@mustermannehotel.de',1,'Max','ee11cbb19052e40b07aac0ca060c23ee','ROLE_USER','Max');

INSERT INTO `zimmerkategorie` (`id`,`preis`, `zimmertyp`) VALUES (1,49, 'Single room' ), (2,99, 'Double room' ), (3,199, 'Suite' );

INSERT INTO `zimmer` ( `zimmer_nr`, `zimmerkategorie_id`) VALUES ( 110 , 1  ), ( 111 , 1  ), ( 112 , 1  ), ( 210 , 2  ), ( 211 , 2  ), ( 310 , 3  );

INSERT INTO `zusatz_service` (`anzahl`, `name`, `preis`) VALUES ( 3, 'car', 130 ), ( 4, 'meeting room', 200 ), ( 5, 'wellness day', 150 ), ( 5, 'room service', 50 );