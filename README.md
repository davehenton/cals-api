# CWDS CALS API

The CWDS CALS API provides RESTful services for the CWDS CALS Digital Service.

## Wiki

The development team is actively using the [Github Wiki](https://github.com/ca-cwds/cals-api/wiki).

## Documentation

The development team uses [Swagger](http://swagger.io/) for documenting the API.
NOTE : At this time there is not a publicy available link to the documentation, a link will be provided as soon as one is available.


## Configuration

**Application Configuration Parameters**
- APP_VERSION -- Version of application

**SSL Configuration Parameters**
- KEY_STORE_FILE -- Path to keystore file
- KEY_STORE_PASSWORD -- Keystore password

**Data Stores Configuration Parameters**

The CWDS API currently utilizes two persistent stores:

1. DB2 - CMS database
2. Postgres - NS database

In order for the CALS API successfully connect to the above databases the following environment variables are required to be set:

- DB_CMS_USER -- the CMS database username
- DB_CMS_PASSWORD -- the CMS database password
- DB_CMS_JDBC_URL -- the CMS database URL in Java Database Connectivity format
- DB_CMS_SCHEMA -- the CMS database schema the tables belong to.
- DB_CMS_CP_INITIAL_SIZE -- the CMS connections pool iniitial size  
- DB_CMS_CP_MIN_SIZE -- the CMS connections pool minimum size
- DB_CMS_CP_MAX_SIZE -- the CMS connections pool maximum size


- DB_NS_USER -- the NS database username
- DB_NS_PASSWORD -- the NS database password
- DB_NS_JDBC_URL -- the NS database URL in Java Database Connectivity format
- DB_NS_CP_INITIAL_SIZE -- the NS connections pool iniitial size  
- DB_NS_CP_MIN_SIZE -- the NS connections pool minimum size
- DB_NS_CP_MAX_SIZE -- the NS connections pool maximum size

**Swagger Configuration Parameters**

- LOGIN_URL -- Login URL
- SHOW_SWAGGER -- Show swagger (true | false)

**Shiro Configuration Parameters**
- SHIRO_CONFIG_PATH -- path to Shiro configuration file
 
The Docker env-file option provides a convenient method to supply these variables. These instructions assume an env file called .env located in the current directory. The repository contains a sample env file called env.sample.

Further configuration options are available in the file config/cals-api.yml.

