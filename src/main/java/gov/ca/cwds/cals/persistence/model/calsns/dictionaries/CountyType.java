package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

/**
 * @author CWDS CALS API Team.
 */

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = CountyType.NAMED_QUERY_FIND_ALL, query = "FROM CountyType ORDER BY id ASC")
@Entity
@Table(name = "county_type")
public class CountyType extends BaseDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".CountyType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = -8822705657189386895L;

}
