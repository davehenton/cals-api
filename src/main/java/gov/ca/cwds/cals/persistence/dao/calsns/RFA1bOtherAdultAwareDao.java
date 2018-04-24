package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2 Team.
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public class RFA1bOtherAdultAwareDao extends RFA1bDao {

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  public RFA1bOtherAdultAwareDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Creates RFA1bForm for an OtherAdult.
   *
   * @param rfa1bForm RFA1bForm instance
   * @return stored RFA1bForm
   */
  public RFA1bForm create(RFA1bForm rfa1bForm) {
    RFA1aOtherAdult otherAdult = Optional.ofNullable(rfa1bForm.getOtherAdult()).orElseThrow(
        () -> new IllegalArgumentException("RFA1bForm must have reference to OtherAdult entity"));
    RFA1bForm rfa1bFormEntity = super.create(rfa1bForm);
    otherAdult.setRfa1bForm(rfa1bFormEntity);
    otherAdultDao.update(otherAdult);
    return rfa1bFormEntity;
  }

  @Override
  protected void afterDelete(RFA1bForm deleted) {
    Optional.ofNullable(deleted).ifPresent(rfa1bForm ->
        Optional.ofNullable(rfa1bForm.getOtherAdult()).ifPresent(otherAdult -> {
          otherAdult.setRfa1bForm(null);
          otherAdultDao.update(otherAdult);
        })
    );
  }
}
