package gov.ca.cwds.cals.service.tracking;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingTemplateDao;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.tracking.builder.TrackingBuilder;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;

public class RFA1ATrackingService extends TypedCrudServiceAdapter<Long, Tracking, Tracking> {
  @Inject
  private RFA1aFormService rfa1aFormService;
  @Inject
  private TrackingDao trackingDao;
  @Inject
  private TrackingTemplateDao trackingTemplateDao;

  public Tracking create(Tracking tracking) {
    RFA1aFormsParameterObject rfa1aFormsParameterObject = new RFA1aFormsParameterObject(tracking.getRfa1aId());
    RFA1aFormDTO rfa1aFormDTO = rfa1aFormService.find(rfa1aFormsParameterObject);
    JsonNode trackingDocuments = new TrackingBuilder().build(rfa1aFormDTO, getTrackingTemplates());
    tracking.setTrackingJson(trackingDocuments);
    tracking.setFacilityName(rfa1aFormDTO.getFacilityName());
    return trackingDao.create(tracking);
  }

  private List<TrackingTemplate> getTrackingTemplates() {
    //TODO
    return null;
  }
}
