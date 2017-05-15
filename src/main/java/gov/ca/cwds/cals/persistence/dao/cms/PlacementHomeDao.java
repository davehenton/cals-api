package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.OutOfHomePlacement;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */
public class PlacementHomeDao extends BaseDaoImpl<PlacementHome> {
    private static final Logger LOG = LoggerFactory.getLogger(PlacementHomeDao.class);

    @Inject
    public PlacementHomeDao(@CmsSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public PlacementHome findChildren(String facilityNumber) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createNamedQuery("gov.ca.cwds.cals.model.cms.PlacementHome.findChildren");
        query.setParameter("facilityNumber", facilityNumber);
        PlacementHome placementHome = null;
        try {
            placementHome = (PlacementHome) query.getSingleResult();
        } catch (NoResultException e) {
            LOG.warn("There is no result for facilityNumber = " + facilityNumber, e);
        }
        return placementHome;
    }

    public PlacementHome findChild(String facilityNumber, String childId) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createNamedQuery("gov.ca.cwds.cals.model.cms.PlacementHome.findChild");
        query.setParameter("facilityNumber", facilityNumber);
        query.setParameter("childId", childId);
        //todo: refactor. try to query through client entity
        PlacementHome placementHome = null;
        try {
            Object[] result = (Object[]) query.getSingleResult();
            placementHome = (PlacementHome) result[0];
            OutOfHomePlacement outOfHomePlacement = (OutOfHomePlacement) result[1];
            Set<OutOfHomePlacement> outOfHomePlacementSet = new HashSet<>();
            outOfHomePlacementSet.add(outOfHomePlacement);
            placementHome.setOutOfHomePlacements(outOfHomePlacementSet);
        } catch (NoResultException e) {
            LOG.warn("There is no result for facilityNumber = " + facilityNumber + " and childId = " + childId, e);
        }

        return placementHome;
    }

}