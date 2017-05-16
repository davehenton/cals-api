package gov.ca.cwds.cals.persistence.dao;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintReportLic802Dao extends BaseDaoImpl<ComplaintReportLic802> {

    @Inject
    public ComplaintReportLic802Dao(@FasSessionFactory SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
