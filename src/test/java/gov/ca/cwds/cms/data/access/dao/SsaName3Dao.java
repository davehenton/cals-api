package gov.ca.cwds.cms.data.access.dao;

import gov.ca.cwds.data.legacy.cms.dao.SsaName3ParameterObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */

public class SsaName3Dao {

  private static final Logger LOGGER = LoggerFactory.getLogger(SsaName3Dao.class);

  public void callStoredProc(SsaName3ParameterObject parameterObject) {
    LOGGER.info("Calling store proc SsaName3 with parameters: {} {} {} {} {} {} {} {} {} {} {} {}",
        parameterObject.getTableName(), parameterObject.getCrudOper(),
        parameterObject.getIdentifier(),
        parameterObject.getNameCd(), parameterObject.getFirstName(),
        parameterObject.getMiddleName(),
        parameterObject.getLastName(), parameterObject.getStreettNumber(),
        parameterObject.getStreetName(),
        parameterObject.getGvrEntc(), parameterObject.getUpdateTimeStamp(),
        parameterObject.getUpdateId());
  }

}

