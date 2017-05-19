package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class FacilityTypeDao extends BaseDaoImpl<FacilityType> {
    private static final Logger LOG = LoggerFactory.getLogger(FacilityTypeDao.class);

    @Inject
    public FacilityTypeDao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
