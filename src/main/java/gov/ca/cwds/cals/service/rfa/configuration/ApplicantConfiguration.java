package gov.ca.cwds.cals.service.rfa.configuration;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantConfiguration
    implements RFAExternalEntityConfiguration<RFAApplicant, Applicant, ApplicantsDTO> {

  public static final RFAExternalEntityConfiguration<RFAApplicant, Applicant, ApplicantsDTO>
      INSTANCE = new ApplicantConfiguration();

  private ApplicantConfiguration() {
  }

  @Override
  public Applicant createEntityDTO() {
    return new Applicant();
  }

  @Override
  public RFAApplicant createEntity() {
    return new RFAApplicant();
  }

  @Override
  public ApplicantsDTO createEntitiesDTO(List<Applicant> collectDTOs) {
    return new ApplicantsDTO(collectDTOs);
  }
}
