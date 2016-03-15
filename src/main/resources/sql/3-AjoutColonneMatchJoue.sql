ALTER TABLE `pronos`.`resultats` 
ADD COLUMN `matchJoue` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '' AFTER `idMatch`;
