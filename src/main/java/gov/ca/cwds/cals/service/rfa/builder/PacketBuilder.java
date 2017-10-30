package gov.ca.cwds.cals.service.rfa.builder;

import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.cals.service.dto.packet.PersonSummaryDTO;
import gov.ca.cwds.cals.service.dto.packet.SectionSummaryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RelationshipToApplicantDTO;
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

    rfa1aSections.add(new SectionSummaryDTO("applicants",
        (null != rfa1aFormDTO.getApplicants()) && !rfa1aFormDTO.getApplicants().isEmpty()));

    rfa1aSections.add(new SectionSummaryDTO("residence",
        null != rfa1aFormDTO.getResidence()));

    rfa1aSections.add(new SectionSummaryDTO("applicants_relationship",
        null != rfa1aFormDTO.getApplicantsRelationship()));

    rfa1aSections.add(new SectionSummaryDTO("minor_children",
        (null != rfa1aFormDTO.getMinorChildren()) && !rfa1aFormDTO.getMinorChildren().isEmpty()));

    rfa1aSections.add(new SectionSummaryDTO("other_adults",
        (null != rfa1aFormDTO.getOtherAdults()) && !rfa1aFormDTO.getOtherAdults().isEmpty()));

    rfa1aSections.add(new SectionSummaryDTO("applicants_history",
        null != rfa1aFormDTO.getApplicantsHistory()));

    rfa1aSections.add(new SectionSummaryDTO("child_desired",
        null != rfa1aFormDTO.getChildDesired()));

    rfa1aSections.add(new SectionSummaryDTO("foster_care",
        null != rfa1aFormDTO.getAdoptionHistory()));

    rfa1aSections.add(new SectionSummaryDTO("references",
        null != rfa1aFormDTO.getReferences()));

    rfa1aSections.add(new SectionSummaryDTO("applicants_declaration",
        null != rfa1aFormDTO.getApplicantsDeclaration()));

    this.rfa1aSections = rfa1aSections;

    return this;
  }

  public PacketBuilder applicants(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> applicants = new ArrayList<>();

    if ((null != rfa1aFormDTO.getApplicants()) && !rfa1aFormDTO.getApplicants().isEmpty()) {

      for (ApplicantDTO applicant : rfa1aFormDTO.getApplicants()) {
        applicants.add(new PersonSummaryDTO(applicant.getId(), applicant.getFirstName(),
            applicant.getLastName(), null != applicant.getRfa1bForm()));
      }
    }
    this.applicants = applicants;

    return this;
  }

  public PacketBuilder otherAdults(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> otherAdults = new ArrayList<>();

    if ((null != rfa1aFormDTO.getOtherAdults()) && !rfa1aFormDTO.getOtherAdults().isEmpty()) {
      for (OtherAdultDTO otherAdult : rfa1aFormDTO.getOtherAdults()) {
        otherAdults.add(new PersonSummaryDTO(otherAdult.getId(), otherAdult.getFirstName(),
            otherAdult.getLastName(), null != otherAdult.getRfa1bForm()));
      }
    }
    this.otherAdults = otherAdults;

    return this;
  }

  public PacketBuilder adultChildren(RFA1aFormDTO rfa1aFormDTO) {
    List<PersonSummaryDTO> adultChildren = new ArrayList<>();

    if ((null != rfa1aFormDTO.getOtherAdults()) && !rfa1aFormDTO.getOtherAdults().isEmpty()
        && (null != rfa1aFormDTO.getApplicantsRelationship())) {

      for (OtherAdultDTO otherAdult : rfa1aFormDTO.getOtherAdults()) {

        for (RelationshipToApplicantDTO relationshipToApplicant : otherAdult
            .getRelationshipToApplicants()) {

          if (relationshipToApplicant.getRelationshipToApplicantType().getValue()
              .equalsIgnoreCase("Child")) {
            adultChildren.add(new PersonSummaryDTO(otherAdult.getId(), otherAdult.getFirstName(),
                otherAdult.getLastName(), null != otherAdult.getRfa1bForm()));
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
}
