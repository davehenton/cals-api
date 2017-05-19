package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class CountiesDao extends BaseDaoImpl<County> {

    @Inject
    public CountiesDao(@CmsSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<County> findAll() {
        Session session = this.getSessionFactory().getCurrentSession();
        org.hibernate.Query query = session.getNamedQuery(County.NQ_ALL);
        ImmutableList.Builder<County> entities = new ImmutableList.Builder();
        entities.addAll(query.list());
        return entities.build();
    }

}
