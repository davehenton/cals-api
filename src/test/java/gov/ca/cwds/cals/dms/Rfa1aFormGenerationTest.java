package gov.ca.cwds.cals.dms;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aAdoptionHistoryResourceTest.ADOPTION_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANT_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsDeclarationResourceTest.APPLICANTS_DECLARATION_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsHistoryResourceTest.APPLICANTS_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsRelationshipResourceTest.APPLICANTS_RELATIONSHIP_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aChildDesiredResourceTest.CHILD_DESIRED_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.RFA_1A_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResourceTest.MINOR_CHILDREN_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResourceTest.OTHER_ADULTS_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aReferencesResourceTest.REFERENCES_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResourceTest.RESIDENCE_FIXTURE;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.AdultChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationshipDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.FormerSpouseDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import io.dropwizard.jackson.Jackson;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author TPT-2 team
 */
public class Rfa1aFormGenerationTest extends AbstractFormGenerationTest {
  private ObjectMapper mapper = Jackson.newObjectMapper();

  @Test
  public void testPdfGeneration() throws Exception {
    String templatePath = "dms/rfa1a-case/RFA-01A Resource Family Application.pdf";
    String scriptPath = "dms/rfa1a-form/RFA-1A-form.groovy";

    RFA1aFormDTO rfa1aForm = mapper.readValue(RFA_1A_FIXTURE, RFA1aFormDTO.class);
    rfa1aForm.setId(100L);

    Long applicant1Id = 1L;
    Long applicant2Id = 2L;

    ApplicantDTO applicant1 = getApplicant(applicant1Id, 1);
    ApplicantDTO applicant2 = getApplicant(applicant2Id, 2);

    List<ApplicantDTO> applicants = new ArrayList<>();
    applicants.add(applicant1);
    applicants.add(applicant2);
    rfa1aForm.setApplicants(applicants);


    ResidenceDTO residenceDTO = mapper.readValue(RESIDENCE_FIXTURE, ResidenceDTO.class);
    rfa1aForm.setResidence(residenceDTO);


    ApplicantsRelationshipDTO applicantsRelationshipDTO = mapper.readValue(APPLICANTS_RELATIONSHIP_FIXTURE, ApplicantsRelationshipDTO.class);
    rfa1aForm.setApplicantsRelationship(applicantsRelationshipDTO);


    MinorChildDTO minorChild1 = getMinorChild(applicant1Id, 1);
    MinorChildDTO minorChild2 = getMinorChild(applicant1Id, 2);
    MinorChildDTO minorChild3 = getMinorChild(applicant2Id, 3);
    MinorChildDTO minorChild4 = getMinorChild(applicant2Id, 4);

    List<MinorChildDTO> minorChildren = new ArrayList<>();
    minorChildren.add(minorChild1);
    minorChildren.add(minorChild2);
    minorChildren.add(minorChild3);
    minorChildren.add(minorChild4);
    rfa1aForm.setMinorChildren(minorChildren);


    OtherAdultDTO otherAdult11 = getOtherAdult(applicant1Id, 1);
    OtherAdultDTO otherAdult12 = getOtherAdult(applicant1Id, 2);
    OtherAdultDTO otherAdult21 = getOtherAdult(applicant2Id, 3);
    OtherAdultDTO otherAdult22 = getOtherAdult(applicant2Id, 4);

    List<OtherAdultDTO> otherAdults = new ArrayList<>();
    otherAdults.add(otherAdult11);
    otherAdults.add(otherAdult12);
    otherAdults.add(otherAdult21);
    otherAdults.add(otherAdult22);
    rfa1aForm.setOtherAdults(otherAdults);


    ApplicantsHistoryDTO applicantsHistoryDTO = mapper.readValue(APPLICANTS_HISTORY_FIXTURE, ApplicantsHistoryDTO.class);

    List<FormerSpouseDTO> formerSpouses = applicantsHistoryDTO.getFormerSpouses();
    FormerSpouseDTO formerSpouseDTO = formerSpouses.remove(0);
    String formerSpouseJson = mapper.writeValueAsString(formerSpouseDTO);

    FormerSpouseDTO formerSpouse11 =  getFormerSpouse(formerSpouseJson, applicant1Id);
    FormerSpouseDTO formerSpouse12 =  getFormerSpouse(formerSpouseJson, applicant1Id);
    FormerSpouseDTO formerSpouse21 =  getFormerSpouse(formerSpouseJson, applicant2Id);
    FormerSpouseDTO formerSpouse22 =  getFormerSpouse(formerSpouseJson, applicant2Id);

    formerSpouses.add(formerSpouse11);
    formerSpouses.add(formerSpouse12);
    formerSpouses.add(formerSpouse21);
    formerSpouses.add(formerSpouse22);
    applicantsHistoryDTO.setFormerSpouses(formerSpouses);

    List<AdultChildDTO> adultChildren = applicantsHistoryDTO.getAdultChildren();
    AdultChildDTO adultChildDTO = adultChildren.remove(0);
    String adultChildJson = mapper.writeValueAsString(adultChildDTO);
    AdultChildDTO adultChild11 = getAdultChild(adultChildJson, applicant1Id);
    AdultChildDTO adultChild12 = getAdultChild(adultChildJson, applicant1Id);
    AdultChildDTO adultChild21 = getAdultChild(adultChildJson, applicant2Id);
    AdultChildDTO adultChild22 = getAdultChild(adultChildJson, applicant2Id);

    //Rename

    adultChild11.setFirstName(adultChild11.getFirstName() + "_1");
    adultChild12.setFirstName(adultChild12.getFirstName() + "_2");
    adultChild21.setFirstName(adultChild21.getFirstName() + "_3");
    adultChild22.setFirstName(adultChild22.getFirstName() + "_4");

    adultChildren.add(adultChild11);
    adultChildren.add(adultChild12);
    adultChildren.add(adultChild21);
    adultChildren.add(adultChild22);
    applicantsHistoryDTO.setAdultChildren(adultChildren);
    rfa1aForm.setApplicantsHistory(applicantsHistoryDTO);

    ChildDesiredDTO childDesiredDTO = mapper.readValue(CHILD_DESIRED_FIXTURE, ChildDesiredDTO.class);
    rfa1aForm.setChildDesired(childDesiredDTO);


    AdoptionHistoryDTO adoptionHistoryDTO = mapper.readValue(ADOPTION_HISTORY_FIXTURE, AdoptionHistoryDTO.class);
    rfa1aForm.setAdoptionHistory(adoptionHistoryDTO);


    ReferencesDTO referencesDTO = mapper.readValue(REFERENCES_FIXTURE, ReferencesDTO.class);
    rfa1aForm.setReferences(referencesDTO);


    ApplicantsDeclarationDTO applicantsDeclarationDTO = mapper.readValue(APPLICANTS_DECLARATION_FIXTURE, ApplicantsDeclarationDTO.class);
    List<ApplicantsDeclarationDTO.ApplicantSignature> applicantSignatures = applicantsDeclarationDTO.getApplicantSignatures();
    ApplicantsDeclarationDTO.ApplicantSignature applicantSignature = applicantSignatures.remove(0);
    String applicantSignatureJson = mapper.writeValueAsString(applicantSignature);

    ApplicantsDeclarationDTO.ApplicantSignature applicantSignature1 = getApplicantSignature(applicantSignatureJson, applicant1Id);
    ApplicantsDeclarationDTO.ApplicantSignature applicantSignature2 = getApplicantSignature(applicantSignatureJson, applicant2Id);
    applicantSignatures.add(applicantSignature1);
    applicantSignatures.add(applicantSignature2);
    rfa1aForm.setApplicantsDeclaration(applicantsDeclarationDTO);

    String request = mapper.writeValueAsString(rfa1aForm);

    generateAndAssertPdf(templatePath, fixture(scriptPath), "{}"); //Check generation for empty form
    generateAndAssertPdf(templatePath, fixture(scriptPath), request);
  }

  private ApplicantDTO getApplicant(Long id, int seed) throws Exception {
    ApplicantDTO applicant = mapper.readValue(APPLICANT_FIXTURE, ApplicantDTO.class);
    applicant.setId(id);
    applicant.setFirstName(applicant.getFirstName() + seed);
    applicant.setLastName(applicant.getLastName() + seed);
    return applicant;
  }

  private MinorChildDTO getMinorChild(Long applicantId, int seed) throws Exception {
    MinorChildDTO minorChild = mapper.readValue(MINOR_CHILDREN_FIXTURE, MinorChildDTO.class);
    minorChild.setOtherRelativeFirstName(minorChild.getOtherRelativeFirstName() + seed);
    minorChild.setOtherRelativeLastName(minorChild.getOtherRelativeLastName() + seed);
    minorChild.getRelationshipToApplicants().get(0).setApplicantId(applicantId);
    return minorChild;
  }

  public OtherAdultDTO getOtherAdult(Long applicantId, int seed) throws Exception {
    OtherAdultDTO otherAdult = mapper.readValue(OTHER_ADULTS_FIXTURE, OtherAdultDTO.class);
    otherAdult.setFirstName(otherAdult.getFirstName() + seed);
    otherAdult.setLastName(otherAdult.getLastName() + seed);
    otherAdult.getRelationshipToApplicants().get(0).setApplicantId(applicantId);
    return otherAdult;
  }

  public FormerSpouseDTO getFormerSpouse(String formerSpouseJson, Long applicantId) throws Exception {
    FormerSpouseDTO formerSpouse = mapper.readValue(formerSpouseJson, FormerSpouseDTO.class);
    formerSpouse.setApplicantId(applicantId);
    return formerSpouse;
  }

  private AdultChildDTO getAdultChild(String adultChildJson, Long applicant1Id) throws Exception {
    AdultChildDTO adultChild = mapper.readValue(adultChildJson, AdultChildDTO.class);
    adultChild.getRelationshipToApplicants().get(0).setApplicantId(applicant1Id);
    return adultChild;
  }

  private ApplicantsDeclarationDTO.ApplicantSignature getApplicantSignature(String applicantSignatureJson, Long applicantId) throws Exception {
    ApplicantsDeclarationDTO.ApplicantSignature applicantSignature
        = mapper.readValue(applicantSignatureJson, ApplicantsDeclarationDTO.ApplicantSignature.class);
    applicantSignature.setApplicantId(applicantId);
    return applicantSignature;
  }


}
