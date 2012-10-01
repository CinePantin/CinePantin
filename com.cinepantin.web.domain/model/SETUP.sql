-- -------------------------------------------------------
-- FOR DEVELOPMENT ENVIRONMENT ONLY, AND ONLY IF NEEDED --
--     DON'T DO THIS ON YOUR PRODUCTION DB SERVER!      --
-- -------------------------------------------------------

-- This mySql script will set up 
--   - the CinePantin schema,
--   - the CinePantin local user, with all necessary rights on the CinePantin schema
--   - optionaly (uncomment) the CinePantin global user, with all necessary rights on the CinePantin schema 
--

DROP SCHEMA IF EXISTS CinePantin;
CREATE SCHEMA CinePantin;

-- this is a hack by Steve Bearman - see http://bugs.mysql.com/bug.php?id=19166
GRANT USAGE ON CinePantin.* TO 'CinePantin'@'localhost'; 
DROP USER 'CinePantin'@'localhost'; -- hack necessary because of lack of "DROP USER IF EXISTS" in mySql...
CREATE USER 'CinePantin'@'localhost' IDENTIFIED BY 'CinePantin'; 
-- IN YOUR PRODUCTION ENVIRONMENT, USE A STRONG PASSWORD (and maybe another user name ?)

GRANT ALL ON CinePantin.* TO 'CinePantin'@'localhost'; 
-- BE MORE RESTRICTIVE IN YOUR PRODUCTION ENVIRONMENT (SELECT, INSERT, UPDATE, DELETE should be enough)
-- TODO: make an app that doesn't require DELETE? 


-- If you nedd to access the DB server from other hosts in your dev. environment
--  ( * means "any host". Want to be on the safer side? Replace it by IP address, or locally-resolvable name)

 GRANT USAGE ON CinePantin.* TO 'CinePantin'@'*'; -- this is a hack by Steve Bearman - see http://bugs.mysql.com/bug.php?id=19166
 DROP USER 'CinePantin'@'*';
 CREATE USER 'CinePantin'@'*' IDENTIFIED BY 'CinePantin';
 GRANT ALL ON CinePantin.* TO 'CinePantin'@'*';

FLUSH PRIVILEGES;