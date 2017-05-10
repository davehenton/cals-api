package gov.ca.cwds.cals.persistence.dao;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class LisFacFileDao extends BaseDaoImpl<LisFacFile> {

    @Inject
    public LisFacFileDao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
