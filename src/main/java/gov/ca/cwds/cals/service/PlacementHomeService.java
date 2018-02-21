package gov.ca.cwds.cals.service;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.formsapi.FormInstanceDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormNameAware;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.AddressDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.CommonInfoDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.EmergencyContactDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.EndDateDTO;
import gov.ca.cwds.cals.service.dto.placementhome.otherchildren.OtherChildrenDTO;
import gov.ca.cwds.cals.service.mapper.EmergencyContactMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeAddressMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeCommonInfoMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeEndDateMapper;
import gov.ca.cwds.cms.data.access.dao.EmergencyContactDetailDao;
import gov.ca.cwds.data.legacy.cms.dao.PlacementHomeDao;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.rest.api.Response;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PlacementHomeService extends CrudServiceAdapter {

  private static final String SCHEMA_VERSION = "1";

  @Inject
  private EmergencyContactDetailDao emergencyContactDetailDao;

  @Inject
  private PlacementHomeDao placementHomeDao;

  @Inject
  private EmergencyContactMapper emergencyContactMapper;

  @Inject
  private PlacementHomeCommonInfoMapper placementHomeCommonInfoMapper;

  @Inject
  private PlacementHomeEndDateMapper placementHomeEndDateMapper;

  @Inject
  private PlacementHomeAddressMapper placementHomeAddressMapper;


  @Override
  public Response find(Serializable params) {
    return getPlacementHomePackage((String) params);
  }

  private FormsPackageDTO getPlacementHomePackage(String placementHomeId) {
    PlacementHome placementHome = placementHomeDao.find(placementHomeId);
    if (placementHome == null) {
      return null;
    }
    FormsPackageDTO result = new FormsPackageDTO();
    result.setExternalEntityId(placementHomeId);
    result.setDescription("Some Description");
    result.getFormInstances().addAll(getFormInstances(placementHome));
    return result;
  }

  private List<FormInstanceDTO> getFormInstances(PlacementHome placementHome) {
    List<FormInstanceDTO> formInstanceDTOList = new ArrayList<>();
    formInstanceDTOList.add(getFormForPlacementHome(placementHome));
    formInstanceDTOList.add(getFormForEmergencyContact(placementHome.getIdentifier()));
    formInstanceDTOList.add(getFormForEndDate(placementHome));
    formInstanceDTOList.add(getFormForAddress(placementHome));
    formInstanceDTOList.add(getMockedOtherChildren());
    return formInstanceDTOList;
  }

  private FormInstanceDTO getFormForPlacementHome(PlacementHome placementHome) {
    return generateForm(placementHomeCommonInfoMapper.toCommonInfoDTO(placementHome), CommonInfoDTO.PH_PAGE_ID_COMMON_INFO);
  }

  private FormInstanceDTO getFormForEndDate(PlacementHome placementHome) {
    return generateForm(placementHomeEndDateMapper.toEndDateDTO(placementHome), EndDateDTO.PH_PAGE_ID_END_DATE);
  }

  private FormInstanceDTO getFormForAddress(PlacementHome placementHome) {
    return generateForm(placementHomeAddressMapper.toAddressDTO(placementHome), AddressDTO.PH_PAGE_ID_ADDRESS);
  }

  private FormInstanceDTO getFormForEmergencyContact(String placementHomeId) {
    EmergencyContactDetail emergencyContactDetail = emergencyContactDetailDao.findByEstblshId(placementHomeId);
    EmergencyContactDTO emergencyContactDTO = emergencyContactMapper.toEmergencyContactDTO(emergencyContactDetail);
    return generateForm(emergencyContactDTO, EmergencyContactDTO.PH_PAGE_ID_EMERGENCY_CONTACT);
  }

  private FormInstanceDTO generateForm(FormNameAware formNameAware, String formName) {
    FormInstanceDTO formInstance = new FormInstanceDTO();
    formInstance.setName(formName);
    formInstance.setSchemaVersion(SCHEMA_VERSION);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.valueToTree(formNameAware);
    formInstance.setContent(node);
    return formInstance;

  }

  private FormInstanceDTO getMockedOtherChildren() {
    FormInstanceDTO formInstance = new FormInstanceDTO();
    formInstance.setName(OtherChildrenDTO.PH_PAGE_OTHER_CHILDREN);
    formInstance.setSchemaVersion(SCHEMA_VERSION);
    formInstance.setContent(getContent("other_children"));
    return formInstance;
  }

  private static JsonNode getContent(String fileName) {
    ObjectMapper mapper = new ObjectMapper();
    String json = fixture("fixtures/"+fileName+".json");
    try {
      return mapper.readTree(json);
    } catch (IOException e) {
      return null;
    }
  }

  


}




