package gov.ca.cwds.cals.service.tracking;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingDao;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;

/**
 * Tracking Service.
 *
 * @author CWDS TPT-2 Team.
 */
public class TrackingService extends TypedCrudServiceAdapter<Long, Tracking, Tracking> {

  @Inject
  private TrackingDao trackingDao;

  @Override
  public Tracking find(Long id) {
    return trackingDao.find(id);
  }

  @Override
  public Tracking delete(Long id) {
    return trackingDao.delete(id);
  }

  @Override
  public Tracking create(Tracking tracking) {
    return trackingDao.create(tracking);
  }

  @Override
  public Tracking update(Long id, Tracking tracking) {
    tracking.setId(id);
    return trackingDao.update(tracking);
  }
}
