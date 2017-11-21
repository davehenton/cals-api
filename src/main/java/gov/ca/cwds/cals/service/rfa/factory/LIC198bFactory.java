package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.service.dto.rfa.collection.LIC198bFormCollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public class LIC198bFactory
    implements RFAExternalEntityFactory<LIC198bForm, LIC198bFormDTO> {

  public static final RFAExternalEntityFactory<LIC198bForm, LIC198bFormDTO>
      INSTANCE = new LIC198bFactory();

  private LIC198bFactory() {
  }

  @Override
  public LIC198bFormDTO createEntityDTO() {
    return new LIC198bFormDTO();
  }

  @Override
  public LIC198bForm createEntity() {
    return new LIC198bForm();
  }

  @Override
  public Class<LIC198bForm> getEntityClass() {
    return LIC198bForm.class;
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return LIC198bForm.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdAndEntityIdNamedQuery() {
    return LIC198bForm.NAMED_QUERY_FIND_BY_FORMA_ID_AND_FORMB_ID;
  }

  @Override
  public LIC198bFormCollectionDTO createCollectionDTO(List<LIC198bFormDTO> collectDTOs) {
    return new LIC198bFormCollectionDTO(collectDTOs);
  }

}

