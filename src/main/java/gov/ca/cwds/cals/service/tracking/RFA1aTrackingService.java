package gov.ca.cwds.cals.service.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingTemplateDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.tracking.builder.TrackingBuilder;
import gov.ca.cwds.cals.web.rest.parameter.TrackingParameterObject;
import gov.ca.cwds.rest.api.ApiException;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RFA1aTrackingService extends
    TypedCrudServiceAdapter<TrackingParameterObject, Tracking, Tracking> {

  @Inject
  private RFA1aFormsDao rfa1aFormsDao;
  @Inject
  private TrackingDao trackingDao;
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
    return trackingDao.create(tracking);
  }

  @Override
  public Tracking find(TrackingParameterObject params) {
    return trackingDao
        .findByRfa1aIdAndTrackingId(params.getFormId(), params.getTrackingId());
  }

  @Override
  public Tracking update(TrackingParameterObject params, Tracking request) {
    return proceedTrackingAction(params, tracking -> {
      request.setId(params.getTrackingId());
      request.setRfa1aId(params.getFormId());
      return trackingDao.update(request);
    });
  }

  @Override
  public Tracking delete(TrackingParameterObject params) {
    return proceedTrackingAction(params, tracking -> trackingDao.delete(params.getTrackingId()));
  }

  private Tracking proceedTrackingAction(TrackingParameterObject params, Function<Tracking, Tracking> function) {
    return Optional.ofNullable(find(params))
        .map(function).orElseThrow(() -> new ApiException(
            "There is no tracking with Id = " + params.getTrackingId() + " and rfa1aId = " + params
                .getFormId()));
  }

  private RFA1aForm findRfa1a(Tracking tracking) {
    RFA1aForm rfa1aForm = rfa1aFormsDao.find(tracking.getRfa1aId());
    if (rfa1aForm == null) {
      throw new ApiException("RFA1A form [ " + tracking.getRfa1aId() + "] is not found");
    }
    return rfa1aForm;
  }

  private List<TrackingTemplate> findTrackingTemplates() {
    String county = PrincipalUtils.getPrincipal().getCountyCode();
    return trackingTemplateDao.findByCounty(Long.valueOf(county));
  }

  private List<TrackingTemplate> findDefaultTrackingTemplates() {
    return trackingTemplateDao.findByCounty(null);
  }

}
