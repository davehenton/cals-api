package gov.ca.cwds.cals.service.tracking;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingDao;
import gov.ca.cwds.cals.persistence.dao.calsns.TrackingTemplateDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDTO;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDocumentsDTO;
import gov.ca.cwds.cals.service.mapper.TrackingMapper;
import gov.ca.cwds.cals.service.tracking.builder.TrackingDocumentsBuilder;
import gov.ca.cwds.cals.web.rest.parameter.TrackingParameterObject;
import gov.ca.cwds.rest.api.ApiException;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RFA1aTrackingService extends
    TypedCrudServiceAdapter<TrackingParameterObject, TrackingDTO, TrackingDTO> {

  private static final Logger LOG = LoggerFactory.getLogger(RFA1aTrackingService.class);

  @Inject
  private RFA1aFormsDao rfa1aFormsDao;
  @Inject
  private TrackingDao trackingDao;
  @Inject
  private TrackingTemplateDao trackingTemplateDao;
  @Inject
  private TrackingMapper trackingMapper;

  @Override
  public TrackingDTO create(TrackingDTO trackingDTO) {
    RFA1aForm rfa1aForm = findRfa1a(trackingDTO);
    List<TrackingTemplate> templates = findTrackingTemplates();
    List<TrackingTemplate> defaultTemplates = findDefaultTrackingTemplates();
    TrackingDocumentsDTO trackingDocuments = new TrackingDocumentsBuilder()
        .build(rfa1aForm, templates, defaultTemplates);

    trackingDTO.setFacilityName(rfa1aForm.getFacilityName());
    trackingDTO.setTrackingDocuments(trackingDocuments);

    Tracking tracking = trackingMapper.toTracking(trackingDTO);
    return trackingMapper.toTrackingDTO(trackingDao.create(tracking));
  }

  @Override
  public TrackingDTO find(TrackingParameterObject params) {
    return trackingMapper.toTrackingDTO(trackingDao
        .findByRfa1aIdAndTrackingId(params.getFormId(), params.getTrackingId()));
  }

  @Override
  public TrackingDTO update(TrackingParameterObject params, TrackingDTO trackingDTO) {
    return proceedTrackingAction(params, tracking -> {
      trackingDTO.setId(params.getTrackingId());
      trackingDTO.setRfa1aId(params.getFormId());

      return trackingMapper.toTrackingDTO(
          trackingDao.update(
              trackingMapper.toTracking(trackingDTO)
          )
      );
    });
  }

  @Override
  public TrackingDTO delete(TrackingParameterObject params) {
    TrackingDTO result = null;
    try {
      result = proceedTrackingAction(params,
          tracking -> trackingMapper.toTrackingDTO(
              trackingDao.delete(params.getTrackingId())
          ));
    } catch (ApiException e) {
      LOG.info(e.getMessage(), e);
    }
    return result;
  }

  private TrackingDTO proceedTrackingAction(TrackingParameterObject params,
      Function<TrackingDTO, TrackingDTO> function) {
    return Optional.ofNullable(find(params))
        .map(function).orElseThrow(() -> new ApiException(
            "There is no tracking with Id = " + params.getTrackingId() + " and rfa1aId = " + params
                .getFormId()));
  }

  private RFA1aForm findRfa1a(TrackingDTO tracking) {
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
