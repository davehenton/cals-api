package gov.ca.cwds.cals.persistence.dao;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.data.CrudsDaoImpl;
import org.hibernate.SessionFactory;

public class LisFacFileDao extends CrudsDaoImpl<LisFacFile> {

    @Inject
    public LisFacFileDao(@LisSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
