package gov.ca.cwds.cals.service.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingTemplateDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.tracking.builder.TrackingBuilder;
import gov.ca.cwds.rest.api.ApiException;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.util.List;

public class RFA1aTrackingService extends TrackingService {
  @Inject
  private RFA1aFormsDao rfa1aFormsDao;
  @Inject
  private TrackingTemplateDao trackingTemplateDao;

  @Override
  public Tracking create(Tracking tracking) {
    RFA1aForm rfa1aForm = findRfa1a(tracking);
    List<TrackingTemplate> templates = findTrackingTemplates();
    List<TrackingTemplate> defaultTemplates = findDefaultTrackingTemplates();
    JsonNode trackingDocuments = new TrackingBuilder()
        .build(rfa1aForm, templates, defaultTemplates);
    tracking.setTrackingJson(trackingDocuments);
    tracking.setFacilityName(rfa1aForm.getFacilityName());
    return super.create(tracking);
  }

  private RFA1aForm findRfa1a(Tracking tracking) {
    RFA1aForm rfa1aForm = rfa1aFormsDao.find(tracking.getRfa1aId());
    if (rfa1aForm == null) {
      throw new ApiException("RFA1A form [ " + tracking.getRfa1aId() + "] is not found");
    }
    return rfa1aForm;
  }

  private List<TrackingTemplate> findTrackingTemplates() {
    Integer county = Integer.valueOf(PrincipalUtils.getPrincipal().getCountyCwsCode());
    return trackingTemplateDao.findByCounty(county);
  }

  private List<TrackingTemplate> findDefaultTrackingTemplates() {
    return trackingTemplateDao.findByCounty(null);
  }

}
