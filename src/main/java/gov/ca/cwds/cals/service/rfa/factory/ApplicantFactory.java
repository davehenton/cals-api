package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantCollectionDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantFactory
    implements RFAExternalEntityFactory<RFA1aApplicant, Applicant, ApplicantCollectionDTO> {

  public static final RFAExternalEntityFactory<RFA1aApplicant, Applicant, ApplicantCollectionDTO>
      INSTANCE = new ApplicantFactory();

  private ApplicantFactory() {
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
  public Class<RFA1aApplicant> getEntityClass() {
    return RFA1aApplicant.class;
  }

  @Override
  public ApplicantCollectionDTO createEntitiesDTO(List<Applicant> collectDTOs) {
    return new ApplicantCollectionDTO(collectDTOs);
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return RFA1aApplicant.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdandEntityIdNamedQuesry() {
    return RFA1aApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID;
  }

}
