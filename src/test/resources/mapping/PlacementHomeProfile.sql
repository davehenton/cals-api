-- This delineates the specific ethnic groups, religions that a specific PLACEMENT HOME
-- is affiliated with, the specific language spoken within a specific PLACEMENT HOME,
-- the special services that a specific PLACEMENT HOME provide (e.g., drug counseling, etc.).
--   More than one ethnicity, religion, language, special service may be listed for a
-- PLACEMENT HOME.  This information may be used when searching for a  PLACEMENT HOME for
-- a child.

CREATE TABLE HM_PRFT
(
    THIRD_ID char(10) NOT NULL, -- generated base on algorithm
    CHRCTR_C smallint NOT NULL, -- residence.home_languages map to CWS/CMS code
    CHRCTR_CD char(1) NOT NULL, -- L
    LST_UPD_ID char(3) NOT NULL, -- The ID (a sequential 3 digit base 62 number generated by the system) of the STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
    LST_UPD_TS timestamp NOT NULL, -- Current time stamp
    FKPLC_HM_T char(10) NOT NULL, -- Mandatory Foreign key that PROVIDES_HOME_CHARACTERISTIC_FOR a PLACEMENT_HOME.
    CONSTRAINT FKPLC_HM_T PRIMARY KEY (FKPLC_HM_T, THIRD_ID)
);
CREATE UNIQUE INDEX SCPETH33 ON HM_PRFT (THIRD_ID);
CREATE UNIQUE INDEX SCPETH22 ON HM_PRFT (FKPLC_HM_T, CHRCTR_C)