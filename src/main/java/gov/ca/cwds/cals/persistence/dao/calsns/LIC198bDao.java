package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.service.rfa.factory.LIC198bFactory;
import java.io.Serializable;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class LIC198bDao extends RFAExternalEntityDao<LIC198bForm> {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  public LIC198bDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, LIC198bFactory.INSTANCE);
  }

  @Override
  public LIC198bForm create(LIC198bForm object) {
    throw new UnsupportedOperationException(
        "There are other methods for creation of RFA1bForm: createForApplicant and createForOtherAdult");
  }

  public LIC198bForm createForApplicant(LIC198bForm entity, Long applicantId) {
    LIC198bForm created = super.create(entity);
    RFA1aApplicant rfa1aApplicant = applicantDao.find(applicantId);
    rfa1aApplicant.setLic198bForm(created);
    applicantDao.update(rfa1aApplicant);
    return created;
  }

  public LIC198bForm createForOtherAdult(LIC198bForm entity, Long otherAdultId) {
    LIC198bForm created = super.create(entity);
    RFA1aOtherAdult otherAdult = otherAdultDao.find(otherAdultId);
    otherAdult.setLic198bForm(created);
    otherAdultDao.update(otherAdult);
    return created;
  }

  @Override
  public LIC198bForm delete(Serializable id) {
    LIC198bForm entity = this.find(id);
    if (entity != null) {

      Optional.ofNullable(entity.getApplicant()).ifPresent(applicant -> {
        applicant.setLic198bForm(null);
        applicantDao.update(applicant);
      });

      Optional.ofNullable(entity.getOtherAdult()).ifPresent(otherAdult -> {
        otherAdult.setLic198bForm(null);
        otherAdultDao.update(otherAdult);
      });

      this.currentSession().delete(entity);
    }
    return entity;
  }
}
