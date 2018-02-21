package gov.ca.cwds.cals.service;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.formsapi.FormInstanceDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormNameAware;
import gov.ca.cwds.cals.service.dto.formsapi.FormsPackageDTO;
import gov.ca.cwds.cals.service.dto.placementhome.identification.EmergencyContactDTO;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PlacementHomeService extends CrudServiceAdapter {

  private static final Long PACKAGE_ID = 123L;
  private static final String PH_ADDRESS = "PH_page_ID_Address";
  private static final String PH_EMERGENCY_CONTACT = "PH_page_ID_Emergency_Contact";
  private static final String PH_END_DATE = "PH_page_ID_End_Date";
  private static final String PH_COMMON_INFO = "PH_page_ID_common_info";
  private static final String PH_PAGE_OTHER_CHILDREN_CHILD= "PH_page_Other_Children_child";
  private static final String SCHEMA_VERSION = "1";
  private static final SecureRandom random = new SecureRandom();

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
    formInstanceDTOList.addAll(getMockedOtherChildren());
    return formInstanceDTOList;
  }

  private FormInstanceDTO getFormForPlacementHome(PlacementHome placementHome) {
    return generateForm(placementHomeCommonInfoMapper.toCommonInfoDTO(placementHome), PH_COMMON_INFO);
  }

  private FormInstanceDTO getFormForEndDate(PlacementHome placementHome) {
    return generateForm(placementHomeEndDateMapper.toEndDateDTO(placementHome), PH_END_DATE);
  }

  private FormInstanceDTO getFormForAddress(PlacementHome placementHome) {
    return generateForm(placementHomeAddressMapper.toAddressDTO(placementHome), PH_ADDRESS);
  }

  private FormInstanceDTO getFormForEmergencyContact(String placementHomeId) {
    EmergencyContactDetail emergencyContactDetail = emergencyContactDetailDao.findByEstblshId(placementHomeId);
    EmergencyContactDTO emergencyContactDTO = emergencyContactMapper.toEmergencyContactDTO(emergencyContactDetail);
    return generateForm(emergencyContactDTO, PH_EMERGENCY_CONTACT);
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

  private Collection<? extends FormInstanceDTO> getMockedOtherChildren() {
    FormInstanceDTO child1 = getMockedChild("child1");
    FormInstanceDTO child2 = getMockedChild("child2");
    return Arrays.asList(child1, child2);
  }

  private static FormInstanceDTO getMockedChild(String fileName) {
    FormInstanceDTO formInstance = new FormInstanceDTO();
    formInstance.setName(PH_PAGE_OTHER_CHILDREN_CHILD);
    formInstance.setSchemaVersion(SCHEMA_VERSION);
    formInstance.setFormId(String.valueOf(random.nextInt()));
    formInstance.setContent(getContent(fileName));
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




