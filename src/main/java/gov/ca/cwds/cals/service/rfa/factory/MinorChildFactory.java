package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;

/**
 * @author CWDS CALS API Team
 */

public class MinorChildFactory
    implements RFAExternalEntityFactory<RFA1aMinorChild, MinorChild> {

  public static final RFAExternalEntityFactory<RFA1aMinorChild, MinorChild>
      INSTANCE = new MinorChildFactory();

  private MinorChildFactory() {
  }

  @Override
  public MinorChild createEntityDTO() {
    return new MinorChild();
  }

  @Override
  public RFA1aMinorChild createEntity() {
    return new RFA1aMinorChild();
  }

  @Override
  public Class<RFA1aMinorChild> getEntityClass() {
    return RFA1aMinorChild.class;
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return RFA1aMinorChild.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdandEntityIdNamedQuesry() {
    return RFA1aMinorChild.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID;
  }

}

