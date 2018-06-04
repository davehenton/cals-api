package gov.ca.cwds.cals.service.tracking;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingTemplateDao;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;

/**
 * Tracking Service.
 *
 * @author CWDS TPT-2 Team.
 */
public class TrackingTemplateService extends
    TypedCrudServiceAdapter<Long, TrackingTemplate, TrackingTemplate> {

  @Inject
  private TrackingTemplateDao trackingTemplateDao;

  @Override
  public TrackingTemplate find(Long id) {
    return trackingTemplateDao.find(id);
  }

  @Override
  public TrackingTemplate delete(Long id) {
    return trackingTemplateDao.delete(id);
  }

  @Override
  public TrackingTemplate create(TrackingTemplate trackingTemplate) {
    return trackingTemplateDao.create(trackingTemplate);
  }

  @Override
  public TrackingTemplate update(Long id, TrackingTemplate trackingTemplate) {
    trackingTemplate.setId(id);
    return trackingTemplateDao.update(trackingTemplate);
  }
}
