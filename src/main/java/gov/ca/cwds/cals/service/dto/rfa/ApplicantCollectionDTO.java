package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantCollectionDTO extends CollectionDTO<Applicant> {

  private static final long serialVersionUID = -1535264201393941396L;

  public ApplicantCollectionDTO() {
  }

  public ApplicantCollectionDTO(Collection<Applicant> collection) {
    super(collection);
  }
}
