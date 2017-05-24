package gov.ca.cwds.cals.persistence.dao.lis;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class LisFacFileDao extends BaseDaoImpl<LisFacFile> {

    @Inject
    public LisFacFileDao(@LisSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
