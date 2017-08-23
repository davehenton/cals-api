package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;
import java.io.Serializable;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class RFA1bDao extends RFAExternalEntityDao<RFA1bForm> {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  public RFA1bDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, RFA1bFactory.INSTANCE);
  }

  @Override
  public RFA1bForm create(RFA1bForm object) {
    throw new UnsupportedOperationException(
        "There are other methods for creation of RFA1bForm: createForApplicant and createForOtherAdult");
  }

  public RFA1bForm createForApplicant(RFA1bForm dto, Long applicantId) {
    RFA1bForm rfa1bFormEntity = super.create(dto);
    RFA1aApplicant rfa1aApplicant = applicantDao.find(applicantId);
    rfa1aApplicant.setRFA1bForm(rfa1bFormEntity);
    applicantDao.update(rfa1aApplicant);
    return rfa1bFormEntity;
  }

  public RFA1bForm createForOtherAdult(RFA1bForm dto, Long otherAdultId) {
    RFA1bForm rfa1bFormEntity = super.create(dto);
    RFA1aOtherAdult otherAdult = otherAdultDao.find(otherAdultId);
    otherAdult.setRfa1bForm(rfa1bFormEntity);
    otherAdultDao.update(otherAdult);
    return rfa1bFormEntity;
  }

  @Override
  public RFA1bForm delete(Serializable id) {
    RFA1bForm entity = this.find(id);
    if (entity != null) {

      Optional.ofNullable(entity.getApplicant()).ifPresent(applicant -> {
        applicant.setRFA1bForm(null);
        applicantDao.update(applicant);
      });

      Optional.ofNullable(entity.getOtherAdult()).ifPresent(otherAdult -> {
        otherAdult.setRfa1bForm(null);
        otherAdultDao.update(otherAdult);
      });

      this.currentSession().delete(entity);
    }
    return entity;
  }
}
