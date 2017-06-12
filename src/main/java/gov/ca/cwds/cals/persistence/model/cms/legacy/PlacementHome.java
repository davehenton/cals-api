package gov.ca.cwds.cals.persistence.model.cms.legacy;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
        name = "PlacementHome.find",
        query = "SELECT ph FROM PlacementHome ph "
                + " LEFT JOIN FETCH ph.facilityType ft"
                + " LEFT JOIN FETCH ph.county c"
                + " LEFT JOIN FETCH ph.stateCode sc"
                + " LEFT JOIN FETCH ph.licenseStatus ls"
                + " LEFT JOIN FETCH ph.countyLicenseCase cls"
                + " LEFT JOIN FETCH cls.staffPerson sp"
                + " LEFT JOIN FETCH cls.licensingVisits lv"
                + " LEFT JOIN FETCH lv.visitType vt"
//                + " JOIN FETCH sp.county spc"
                + " WHERE ph.identifier = :facilityId"
)
@Entity
@javax.persistence.Table(name = "PLC_HM_T")
public class PlacementHome extends BasePlacementHome {
    private static final long serialVersionUID = 8516376534560115439L;
}
