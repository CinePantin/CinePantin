CREATE USER 'desEssais'@'localhost' IDENTIFIED BY 'desEssais';
CREATE SCHEMA desEssais;
GRANT ALL ON desEssais.* TO 'desEssais'@'localhost';
FLUSH PRIVILEGES;