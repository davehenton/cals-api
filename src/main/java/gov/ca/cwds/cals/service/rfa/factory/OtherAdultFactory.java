package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;

/**
 * @author CWDS CALS API Team
 */

public class OtherAdultFactory
    implements RFAExternalEntityFactory<RFA1aOtherAdult, OtherAdult> {

  public static final RFAExternalEntityFactory<RFA1aOtherAdult, OtherAdult>
      INSTANCE = new OtherAdultFactory();

  private OtherAdultFactory() {
  }

  @Override
  public OtherAdult createEntityDTO() {
    return new OtherAdult();
  }

  @Override
  public RFA1aOtherAdult createEntity() {
    return new RFA1aOtherAdult();
  }

  @Override
  public Class<RFA1aOtherAdult> getEntityClass() {
    return RFA1aOtherAdult.class;
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return RFA1aOtherAdult.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdandEntityIdNamedQuesry() {
    return RFA1aOtherAdult.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID;
  }

}

