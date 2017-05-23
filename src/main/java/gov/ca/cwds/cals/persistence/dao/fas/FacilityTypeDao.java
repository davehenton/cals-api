package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.lis.FacilityType;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityTypeDao extends BaseDaoImpl<FacilityType> {
    private static final Logger LOG = LoggerFactory.getLogger(FacilityTypeDao.class);

    @Inject
    public FacilityTypeDao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<FacilityType> findAll() {
        Session session = this.getSessionFactory().getCurrentSession();
        Query<FacilityType> query = session.createNamedQuery(this.getEntityClass().getName() + ".findAll", FacilityType.class);
        ImmutableList.Builder<FacilityType> entities = new ImmutableList.Builder<>();
        entities.addAll(query.list());
        return entities.build();
    }
}
