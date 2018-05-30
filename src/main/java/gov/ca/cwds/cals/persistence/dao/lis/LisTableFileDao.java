package gov.ca.cwds.cals.persistence.dao.lis;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class LisTableFileDao extends BaseDaoImpl<LisTableFile> {
  private static final Logger LOG = LoggerFactory.getLogger(LisTableFileDao.class);

  @Inject
  public LisTableFileDao(@LisSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<LisTableFile> findAll() {
    return queryImmutableList(LisTableFile.FIND_ALL_QUERY);
  }

  public List<LisTableFile> findAllCounties() {
    return queryImmutableList(LisTableFile.FIND_ALL_COUNTIES_QUERY);
  }

  public List<LisTableFile> findAllFacilityStatuses() {
    return queryImmutableList(LisTableFile.FIND_ALL_FAC_STATUSES_QUERY);
  }

  public List<LisTableFile> findAllFacilityTypes() {
    return queryImmutableList(LisTableFile.FIND_ALL_FAC_TYPES_QUERY);
  }

  public List<LisTableFile> findAllVisitReasons() {
    return queryImmutableList(LisTableFile.FIND_ALL_VISIT_REASONS_QUERY);
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
