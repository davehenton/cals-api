package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = SiblingGroupType.NAMED_QUERY_FIND_ALL, query = "FROM SiblingGroupType ORDER BY id ASC")
@Entity
@Cacheable
@Table(name = "sibling_group_type")
public class SiblingGroupType extends LegacyComplaintDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".SiblingGroupType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = 7042886927755510411L;
}
