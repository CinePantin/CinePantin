

DROP USER IF EXISTS 'desEssais'@'localhost' ;
CREATE USER 'desEssais'@'localhost' IDENTIFIED BY 'desEssais';

-- CREATE USER 'desEssais'@'*' IDENTIFIED BY 'desEssais';
-- CREATE USER 'desEssais'@'192.168.0.204' IDENTIFIED BY 'desEssais';

DROP SCHEMA IF EXISTS desEssais;
CREATE SCHEMA desEssais;
GRANT ALL ON desEssais.* TO 'desEssais'@'localhost';
GRANT ALL ON desEssais.* TO 'desEssais'@'*';
GRANT ALL ON desEssais.* TO 'desEssais'@'192.168.0.204';
FLUSH PRIVILEGES;