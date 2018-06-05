-- This is a license case which is maintained by a county licensing worker.

create table CNTY_CST
(
	ANN_CMP_DT DATE, -- null
	ANN_DUE_DT DATE, -- null
	FIRE_IND CHAR(1) default '' not null, -- "N" Done
	FIRE_R_DT DATE, -- null
	FFH_TYPE SMALLINT not null, -- 3179	0011			  Open Done
	IDENTIFIER CHAR(10) not null -- generated identifier Done
		primary key,
	LST_UPD_ID CHAR(3) not null, -- user staff person id Done
	LST_UPD_TS TIMESTAMP(6) not null, -- update time Done
	LIC_AGE_FR SMALLINT default 0 not null, -- 0 Done
	LIC_AGE_TO SMALLINT default 0 not null, -- 18 Done
	LIC_GNDR CHAR(1) default '' not null, -- 'B' Done
	TRNG_PLAN CHAR(30) default '' not null, -- default '' Done
	PRTY_INFO CHAR(254) default '' not null, -- default '' Done
	TRNG_CMPLT CHAR(254) default '' not null, -- default '' Done
	TRNG_DT DATE, -- null
	FKSTFPERST CHAR(3) not null, -- staff person id default to user created Placemnet Home
	CNTY_SPFCD CHAR(2) not null, -- application_county.id (need to use County Dictionary to map CWS/CMS code)
	CLC_APVC SMALLINT -- null
)
;

create unique index CWSNSS.CNTYCS11
	on CWSNSS.CNTY_CST (FKSTFPERST)
;

