package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = FacilityType.NAMED_QUERY_FIND_ALL, query = "FROM FacilityType ORDER BY id ASC")
@Entity
@Cacheable
@Table(name = "facility_type")
public class FacilityType extends LegacyComplaintDictionary {

  private static final long serialVersionUID = -7703852815579312270L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".FacilityType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}

