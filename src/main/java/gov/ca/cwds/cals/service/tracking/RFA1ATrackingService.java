package gov.ca.cwds.cals.service.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.tracking.Tracking;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.tracking.model.PersonTrackingDocumentsHeader;
import gov.ca.cwds.cals.service.tracking.model.PersonType;
import gov.ca.cwds.cals.service.tracking.model.TrackingDocuments;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;

public class RFA1ATrackingService extends TypedCrudServiceAdapter<Long, Tracking, Tracking> {
  @Inject
  private RFA1aFormService rfa1aFormService;
  private ObjectMapper objectMapper = new ObjectMapper();


  public Tracking create(Tracking tracking) {
    RFA1aFormsParameterObject rfa1aFormsParameterObject = new RFA1aFormsParameterObject(tracking.getApplicationId());
    RFA1aFormDTO rfa1aFormDTO = rfa1aFormService.find(rfa1aFormsParameterObject);
    JsonNode trackingDocuments = buildTrackingDocuments(rfa1aFormDTO);
    tracking.setTrackingDocuments(trackingDocuments);
    //TODO : dao create
    //TODO: headers
    return tracking;
  }

  private JsonNode buildTrackingDocuments(RFA1aFormDTO rfa1aForm) {
    TrackingDocuments trackingDocuments = new TrackingDocuments();
    trackingDocuments.setFacilityDocuments(getFacilityDocumentsTemplate());

    JsonNode applicantTemplate = getApplicantTemplate();

    rfa1aForm.getApplicants().forEach(applicantDTO -> {
        PersonTrackingDocumentsHeader header = new PersonTrackingDocumentsHeader();
        header.setPersonId(applicantDTO.getId());
        header.setPersonName(applicantDTO.getApplicantFullName());
        header.setPersonType(PersonType.APPLICANT);
        JsonNode personTrackingDocuments = merge(header, applicantTemplate);
        trackingDocuments.getPersonTrackingDocuments().add(personTrackingDocuments);
    });

    JsonNode otherAdultTemplate = getApplicantTemplate();

    rfa1aForm.getOtherAdults().forEach(otherAdultDTO -> {
      PersonTrackingDocumentsHeader header = new PersonTrackingDocumentsHeader();
      header.setPersonId(otherAdultDTO.getId());
      header.setPersonName(getOtherAdultName(otherAdultDTO));
      header.setPersonType(PersonType.OTHER_ADULT);
      JsonNode personTrackingDocuments = merge(header, otherAdultTemplate);
      trackingDocuments.getPersonTrackingDocuments().add(personTrackingDocuments);
    });

    return objectMapper.convertValue(trackingDocuments, JsonNode.class);
  }

  private String getOtherAdultName(OtherAdultDTO otherAdultDTO) {
    //TODO: how?
    return "";
  }

  private JsonNode merge(PersonTrackingDocumentsHeader header , JsonNode trackingTemplate) {
    //todo: merge
    return null;
  }



  private JsonNode getFacilityDocumentsTemplate() {
    //TODO: template dao load
    return null;
  }

  private JsonNode getApplicantTemplate() {
    //TODO: template dao load
    return null;
  }

  private JsonNode getOtherAdultTemplate() {
    //TODO: template dao load
    return null;
  }


}
