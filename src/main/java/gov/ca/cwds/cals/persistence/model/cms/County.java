package gov.ca.cwds.cals.persistence.model.cms;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static gov.ca.cwds.cals.persistence.model.cms.County.NQ_ALL;

/**
 *
 * @author CWDS CALS API Team
 *
 */

@NamedQuery(
        name = NQ_ALL,
        query = "FROM County"
)
@NamedQuery(
        name = "County.findByLogicalId",
        query = "FROM County where lgcId = :logicalId"
)
@Entity
@Cacheable
@DiscriminatorValue(value = "GVR_ENTC")
public class County extends SystemCodeTable {

    private static final long serialVersionUID = -6062668668709817218L;

    public static final String NQ_ALL = "County.all";

}
