package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFAServiceHelper {

  private RFAServiceHelper() {
  }

  /**
   * Fills base fields
   *
   * @param entity Entity
   * @param userId User Id
   * @param <T> Type
   * @return Entity whith filled fields
   */
  public static <T extends CalsBaseEntity> T fillCreateBaseFields(T entity, String userId) {
    LocalDateTime now = LocalDateTime.now();
    entity.setCreateDateTime(now);
    entity.setCreateUserId(userId);
    entity.setUpdateDateTime(now);
    entity.setUpdateUserId(userId);
    return entity;
  }

}
