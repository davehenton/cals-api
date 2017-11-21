package gov.ca.cwds.cals.service.rfa.builder;

import static gov.ca.cwds.cals.Constants.API.RFA_1A_ADOPTION_HISTORY;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS_DECLARATION;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS_HISTORY;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_APPLICANTS_RELATIONSHIP;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_CHILD_DESIRED;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_MINOR_CHILDREN;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_OTHER_ADULTS;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_REFERENCES;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_RESIDENCE;

import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.cals.service.dto.packet.PersonSummaryDTO;
import gov.ca.cwds.cals.service.dto.packet.SectionSummaryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.dto.rfa.RelationshipToApplicantDTO;
import gov.ca.cwds.dto.BaseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS TPT-2
 */
public class PacketBuilder {

  private List<SectionSummaryDTO> rfa1aSections;
  private List<PersonSummaryDTO> applicants;
  private List<PersonSummaryDTO> otherAdults;
  private List<PersonSummaryDTO> adultChildren;

  public PacketBuilder rfa1aSections(RFA1aFormDTO rfa1aFormDTO) {
    List<SectionSummaryDTO> rfa1aSections = new ArrayList<>();

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_APPLICANTS,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getApplicants())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_RESIDENCE,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getResidence())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_APPLICANTS_RELATIONSHIP,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getApplicantsRelationship())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_MINOR_CHILDREN,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getMinorChildren())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_OTHER_ADULTS,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getOtherAdults())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_APPLICANTS_HISTORY,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getApplicantsHistory())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_CHILD_DESIRED,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getChildDesired())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_ADOPTION_HISTORY,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getAdoptionHistory())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_REFERENCES,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getReferences())));

    rfa1aSections.add(new SectionSummaryDTO(RFA_1A_APPLICANTS_DECLARATION,
        checkIfSectionIsNotEmpty(rfa1aFormDTO.getApplicantsDeclaration())));

    this.rfa1aSections = rfa1aSections;

    return this;
  }

  public PacketBuilder applicants(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> applicants = new ArrayList<>();

    if (checkIfSectionIsNotEmpty(rfa1aFormDTO.getApplicants())) {
      for (ApplicantDTO applicant : rfa1aFormDTO.getApplicants()) {
        applicants.add(new PersonSummaryDTO(applicant.getId(), applicant.getFirstName(),
            applicant.getLastName(),
            checkIdIfExists(applicant.getRfa1bForm())));
      }
    }
    this.applicants = applicants;

    return this;
  }

  public PacketBuilder otherAdults(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> otherAdults = new ArrayList<>();

    if (checkIfSectionIsNotEmpty(rfa1aFormDTO.getOtherAdults())) {
      for (OtherAdultDTO otherAdult : rfa1aFormDTO.getOtherAdults()) {
        otherAdults.add(new PersonSummaryDTO(otherAdult.getId(), otherAdult.getFirstName(),
            otherAdult.getLastName(),
            checkIdIfExists(otherAdult.getRfa1bForm())));
      }
    }
    this.otherAdults = otherAdults;

    return this;
  }

  public PacketBuilder adultChildren(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> adultChildren = new ArrayList<>();

    if (checkIfSectionIsNotEmpty(rfa1aFormDTO.getOtherAdults()) && checkIfSectionIsNotEmpty(
        rfa1aFormDTO.getApplicantsRelationship())) {
      for (OtherAdultDTO otherAdult : rfa1aFormDTO.getOtherAdults()) {
        for (RelationshipToApplicantDTO relationshipToApplicant : otherAdult.getRelationshipToApplicants()) {
          if (relationshipToApplicant.getRelationshipToApplicantType().getValue()
              .equalsIgnoreCase("Child")) {
            adultChildren.add(new PersonSummaryDTO(otherAdult.getId(), otherAdult.getFirstName(),
                otherAdult.getLastName(),
                checkIdIfExists(otherAdult.getRfa1bForm())));
          }
        }
      }
    }
    this.adultChildren = adultChildren;

    return this;
  }

  public PacketDTO build() {
    return new PacketDTO(this.rfa1aSections,
        this.applicants,
        this.otherAdults,
        this.adultChildren);
  }

  private Long checkIdIfExists(RFAExternalEntityDTO dto) {
    return null != dto ? dto.getId() : null;
  }

  private Boolean checkIfSectionIsNotEmpty(BaseDTO dto) {
    return null != dto;
  }

  private Boolean checkIfSectionIsNotEmpty(List<? extends BaseDTO> dtoList) {
    return (null != dtoList) && !dtoList.isEmpty();
  }
}
