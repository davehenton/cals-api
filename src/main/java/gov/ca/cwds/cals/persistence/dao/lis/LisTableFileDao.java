package gov.ca.cwds.cals.persistence.dao.lis;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;

/**
 * @author CWDS CALS API Team
 */
public class LisTableFileDao extends BaseDaoImpl<LisTableFile> {
  private static final Logger LOG = LoggerFactory.getLogger(LisTableFileDao.class);

  @Inject
  public LisTableFileDao(@LisSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public LisTableFile findCounty(Integer countyCode) {
    return getLisTableFile(
        LisTableFile.FIND_COUNTY_QUERY_PARAM_NAME,
        countyCode,
        LisTableFile.FIND_COUNTY_QUERY_SUFFIX);
  }

  public LisTableFile findFacilityStatus(Integer facilityStatusCode) {
    return getLisTableFile(
        LisTableFile.FIND_FACILITY_STATUS_QUERY_PARAM_NAME,
        facilityStatusCode,
        LisTableFile.FIND_FACILITY_STATUS_QUERY_SUFFIX);
  }

  public LisTableFile findFacilityType(Integer facilityTypeCode) {
    return getLisTableFile(
        LisTableFile.FIND_FACILITY_TYPE_QUERY_PARAM_NAME,
        facilityTypeCode,
        LisTableFile.FIND_FACILITY_TYPE_QUERY_SUFFIX);
  }

  public LisTableFile findVisitReasonType(Integer visitReasonCode) {
    return getLisTableFile(
        LisTableFile.FIND_VISIT_REASON_QUERY_PARAM_NAME,
        visitReasonCode,
        LisTableFile.FIND_VISIT_REASON_QUERY_SUFFIX);
  }

  private LisTableFile getLisTableFile(String paramName, Integer paramCode, String querySuffix) {
    Session session = getSessionFactory().getCurrentSession();
    Class<LisTableFile> entityClass = getEntityClass();
    Query<LisTableFile> query =
        session.createNamedQuery(entityClass.getSimpleName() + querySuffix, entityClass);

    query.setParameter(paramName, paramCode);

    LisTableFile lisTableFile = null;
    try {
      lisTableFile = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn("There is no result for {} = {}", paramName, paramCode);
      LOG.debug(e.getMessage(), e);
    }

    return lisTableFile;
  }
}
