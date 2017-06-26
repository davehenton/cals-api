package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aBaseEntity;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aServiceHelper {

  private RFA1aServiceHelper() {
  }

  public static <T extends RFA1aBaseEntity> T fillCreateBaseFields(T entity, String userId) {
    LocalDateTime now = LocalDateTime.now();
    entity.setCreateDateTime(now);
    entity.setCreateUserId(userId);
    entity.setUpdateDateTime(now);
    entity.setUpdateUserId(userId);
    return entity;
  }

}
