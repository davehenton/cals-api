package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2 Team.
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public class RFA1bApplicantAwareDao extends RFA1bDao {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  public RFA1bApplicantAwareDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Creates RFA1bForm for applicant.
   *
   * @param rfa1bForm RFA1bForm instance
   * @return stored RFA1bForm
   */
  public RFA1bForm create(RFA1bForm rfa1bForm) {
    RFA1aApplicant applicant = Optional.ofNullable(rfa1bForm.getApplicant()).orElseThrow(
        () -> new IllegalArgumentException(
            "RFA1bForm must have reference to RFA1aApplicant entity"));
    RFA1bForm rfa1bFormEntity = super.create(rfa1bForm);
    applicant.setRFA1bForm(rfa1bFormEntity);
    applicantDao.update(applicant);
    return rfa1bFormEntity;
  }


  @Override
  protected void afterDelete(RFA1bForm deleted) {
    Optional.ofNullable(deleted).ifPresent(
        rfa1bForm -> Optional.ofNullable(rfa1bForm.getApplicant()).ifPresent(applicant -> {
          applicant.setRFA1bForm(null);
          applicantDao.update(applicant);
        }));
  }
}
