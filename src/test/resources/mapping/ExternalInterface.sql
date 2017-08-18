-- This entity is used to trigger the external interfaces. When changes occur to a database
-- entity, which are of interest to one of the external interface systems, a row is written
-- to this entity.
CREATE TABLE EXTINF_T
(
    SEQ_NO int NOT NULL,
    SUBMTL_TS timestamp NOT NULL,
    TABLE_NAME char(8) NOT NULL,
    OPER_TYPE char(1) NOT NULL,
    PRM_KEY_1 char(10) NOT NULL,
    PRM_KEY_2 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_3 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_4 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_5 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_6 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_7 char(10) DEFAULT '' NOT NULL,
    PRM_KEY_8 char(10) DEFAULT '' NOT NULL,
    START_DT char(10) DEFAULT '' NOT NULL,
    AST_UNT_CD char(1) DEFAULT '' NOT NULL,
    PERSON_NO char(2) DEFAULT '' NOT NULL,
    SERIAL_NO char(7) DEFAULT '' NOT NULL,
    AID_TPC smallint DEFAULT 0 NOT NULL,
    GVR_ENTC smallint DEFAULT 0 NOT NULL,
    LICENSE_NO char(9) DEFAULT '' NOT NULL,
    RESPONS_DT char(10) DEFAULT '' NOT NULL,
    RECEIVE_DT char(10) DEFAULT '' NOT NULL,
    RAP_ID char(10) DEFAULT '' NOT NULL,
    FBI_IND char(1) DEFAULT '' NOT NULL,
    CRSP_TPC smallint DEFAULT 0 NOT NULL,
    OTHER_DATA char(254) DEFAULT '' NOT NULL,
    L_USERID char(8) DEFAULT '' NOT NULL,
    CONSTRAINT SUBMTL_TS PRIMARY KEY (SUBMTL_TS, SEQ_NO, L_USERID)
)