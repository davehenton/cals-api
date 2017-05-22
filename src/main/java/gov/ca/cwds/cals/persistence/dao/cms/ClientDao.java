package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class ClientDao extends BaseDaoImpl<Client> {
    private static final Logger LOG = LoggerFactory.getLogger(ClientDao.class);

    @Inject
    public ClientDao(@CmsSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Client> findAll(String facilityNumber) {
        Session session = getSessionFactory().getCurrentSession();
        Class<Client> entityClass = getEntityClass();
        Query<Client> query = session.createNamedQuery(entityClass.getName() + ".findAll", entityClass);
        query.setParameter("facilityNumber", facilityNumber);
        ImmutableList.Builder<Client> entities = new ImmutableList.Builder<>();
        entities.addAll(query.list());
        return entities.build();
    }

    public Client find(String facilityNumber, String childId) {
        Session session = getSessionFactory().getCurrentSession();
        Class<Client> entityClass = getEntityClass();
        Query<Client> query = session.createNamedQuery(entityClass.getName() + ".find", entityClass);
        query.setParameter("facilityNumber", facilityNumber);
        query.setParameter("childId", childId);

        //todo: we have duplicates related to different periods. needs more analysis here
        query.setMaxResults(1);

        Client client = null;
        try {
            client = query.getSingleResult();
        } catch (NoResultException e) {
            LOG.warn("There is no result for facilityNumber = " + facilityNumber + " and childId = " + childId, e);
        }

        return client;
    }
}
