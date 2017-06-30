package gov.ca.cwds.cals.service.rfa.configuration;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantConfiguration
    implements RFAExternalEntityConfiguration<RFA1aApplicant, Applicant, ApplicantsDTO> {

  public static final RFAExternalEntityConfiguration<RFA1aApplicant, Applicant, ApplicantsDTO>
      INSTANCE = new ApplicantConfiguration();

  private ApplicantConfiguration() {
  }

  @Override
  public Applicant createEntityDTO() {
    return new Applicant();
  }

  @Override
  public RFA1aApplicant createEntity() {
    return new RFA1aApplicant();
  }

  @Override
  public ApplicantsDTO createEntitiesDTO(List<Applicant> collectDTOs) {
    return new ApplicantsDTO(collectDTOs);
  }
}
