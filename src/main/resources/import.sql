INSERT INTO `admin` (`benutzername`,`email`,`ist_aktiviert`,`nachname`,`password`,`rechte`,`vorname`) VALUES
('admin',NULL,true,NULL,'21232f297a57a5a743894a0e4a801fc3','ROLE_ADMIN',NULL);

INSERT INTO `gast` (`aktivierungs_hash`, `benutzername`, `email`, `ist_aktiviert`, `nachname`, `password`, `rechte`, `vorname`) VALUES
('c6a2df0f8bfe7072fee2eb57573f298b','user','max@mustermannehotel.de','1','Max','ee11cbb19052e40b07aac0ca060c23ee','ROLE_USER','Max');
