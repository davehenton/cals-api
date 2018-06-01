package gov.ca.cwds.cals.persistence.dao.calsns;

import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.time.LocalDateTime;
import org.hibernate.SessionFactory;

/**
 * Tracking Data Access Object.
 *
 * @author CWDS TPT-2 Team
 */
public class TrackingDao extends BaseDaoImpl<Tracking> {
  public TrackingDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public Tracking create(Tracking tracking) {
    LocalDateTime now = LocalDateTime.now();
    tracking.setCreateDateTime(now);
    tracking.setUpdateDateTime(now);
    tracking.setCreateUserId(PrincipalUtils.getStaffPersonId());
    tracking.setUpdateUserId(PrincipalUtils.getStaffPersonId());
    return super.create(tracking);
  }

  @Override
  public Tracking update(Tracking tracking) {
    LocalDateTime now = LocalDateTime.now();
    tracking.setUpdateDateTime(now);
    tracking.setUpdateUserId(PrincipalUtils.getStaffPersonId());
    return super.update(tracking);
  }
}
