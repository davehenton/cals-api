package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.service.builder.PlacementHomeEntityAwareDTOBuilder;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.cms.data.access.service.DataAccessServicesException;
import gov.ca.cwds.cms.data.access.service.impl.PlacementHomeCoreService;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.inject.InjectorHolder;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}.
 *
 * @author CALS API Team
 */
public class FacilityService implements CrudsService {

  @Inject
  private LisFacilityService lisFacilityService;

  @Inject
  private CwsFacilityService cwsFacilityService;

  @Inject
  private FasFacilityService fasFacilityService;

  @Inject
  private PlacementHomeCoreService placementHomeService;

  @Inject
  private FasFacilityMapper fasFacilityMapper;

  @Inject
  private FacilityMapper facilityMapper;

  public FacilityService() {
    // default constructor
  }

  /**
   * Load facility from LIS or CWS/CMS.
   */
  @Override
  public Response find(Serializable params) {
    return findByParameterObject((FacilityParameterObject) params);
  }

  protected FacilityDTO findByParameterObject(FacilityParameterObject parameterObject) {
    FacilityDTO facilityDTO = null;
    if (LIS.equals(parameterObject.getUnitOfWork())) {
      facilityDTO = loadFacilityFromLis(parameterObject);
    } else if (CMS.equals(parameterObject.getUnitOfWork())) {
      facilityDTO = loadFacilityFromCwsCms(parameterObject);
    }
    return facilityDTO;
  }

  private FacilityDTO loadFacilityFromLis(FacilityParameterObject parameterObject) {
    Optional<FacilityDTO> facilityDto = lisFacilityService.loadFacilityFromLis(parameterObject);
    return facilityDto.map(facility -> enrichFacilityWithFasData(parameterObject, facility))
        .orElse(null);
  }

  private FacilityDTO enrichFacilityWithFasData(FacilityParameterObject parameterObject,
      FacilityDTO facilityDto) {
    FacilityInformation facilityInformation =
        fasFacilityService.findFacilityInfoByLicenseNumber(parameterObject);
    if (facilityInformation != null) {
      lisFacilityService.attachVisitsData(facilityInformation);
    }
    fasFacilityMapper.toFacilityDTO(facilityDto, facilityInformation);
    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren =
          cwsFacilityService.findFacilityChildrenByLicenseNumber(
              Integer.valueOf(parameterObject.getFacilityId()));
      List<FacilityInspectionDTO> inspections =
          fasFacilityService.findInspectionsByFacilityId(parameterObject.getFacilityId());
      Set<ComplaintDTO> complaints =
          fasFacilityService.findComplaintsByFacilityId(parameterObject.getFacilityId());
      facilityDto =
          facilityMapper.toExpandedFacilityDTO(
              facilityDto, facilityChildren, inspections, complaints);
    }
    return facilityDto;
  }

  private FacilityDTO loadFacilityFromCwsCms(FacilityParameterObject parameterObject) {
    FacilityDTO facilityDto = cwsFacilityService.loadFacilityFromCwsCms(parameterObject);

    if (parameterObject.isExpanded()) {
      List<FacilityChildDTO> facilityChildren = cwsFacilityService.findFacilityChildrenByFacilityId(
          parameterObject.getFacilityId());
      List<FacilityInspectionDTO> inspections = fasFacilityService.findInspectionsByFacilityId(
          facilityDto.getLicenseNumber());
      Set<ComplaintDTO> complaints = fasFacilityService.findComplaintsByFacilityId(
          facilityDto.getLicenseNumber());
      facilityDto = facilityMapper
          .toExpandedFacilityDTO(facilityDto, facilityChildren, inspections, complaints);
    }

    return facilityDto;
  }

  @Override
  public Response delete(Serializable serializable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Response create(Request request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Response update(Serializable serializable, Request request) {
    throw new UnsupportedOperationException();
  }

  public PlacementHome createPlacementHomeByRfaApplication(RFA1aFormDTO formDTO)
      throws DataAccessServicesException {
    return storePlacementHome(formDTO);
  }

  protected PlacementHome storePlacementHome(RFA1aFormDTO form) throws DataAccessServicesException {
    PlacementHomeEntityAwareDTOBuilder builder = new PlacementHomeEntityAwareDTOBuilder();
    InjectorHolder.INSTANCE.getInjector().injectMembers(builder);
    builder
        .appendForm(form)
        .appendEntity()
        .appendEmergencyContactDetail()
        .appendHomeLanguages()
        .appendSubstituteCareProviders()
        .appendOtherChildrenInHome()
        .appendOtherAdultsInPlacementHome();
    return placementHomeService.create(builder.getPlacementHomeEntityAwareDTO());
  }

}
