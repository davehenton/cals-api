-- This is a license case which is maintained by a county licensing worker.

create table CNTY_CST
(
	ANN_CMP_DT DATE, -- null
	ANN_DUE_DT DATE, -- null
	FIRE_IND CHAR(1) default '' not null, -- "N"
	FIRE_R_DT DATE, -- null
	FFH_TYPE SMALLINT not null, -- 3179	0011			  Open
	IDENTIFIER CHAR(10) not null -- generated identifier
		primary key,
	LST_UPD_ID CHAR(3) not null, -- user staff person id
	LST_UPD_TS TIMESTAMP(6) not null, -- update time
	LIC_AGE_FR SMALLINT default 0 not null, -- 0
	LIC_AGE_TO SMALLINT default 0 not null, -- 18
	LIC_GNDR CHAR(1) default '' not null, -- 'B'
	TRNG_PLAN CHAR(30) default '' not null, -- default ''
	PRTY_INFO CHAR(254) default '' not null, -- default ''
	TRNG_CMPLT CHAR(254) default '' not null, -- default ''
	TRNG_DT DATE, -- null
	FKSTFPERST CHAR(3) not null, -- staff person id default to user created Placemnet Home
	CNTY_SPFCD CHAR(2) not null, -- application_county.id (need to use County Dictionary to map CWS/CMS code)
	CLC_APVC SMALLINT -- null
)
;

create unique index CWSNSS.CNTYCS11
	on CWSNSS.CNTY_CST (FKSTFPERST)
;

