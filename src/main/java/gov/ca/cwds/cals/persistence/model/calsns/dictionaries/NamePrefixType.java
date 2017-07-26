package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = NamePrefixType.NAMED_QUERY_FIND_ALL, query = "FROM NamePrefixType ORDER BY value ASC")
@Entity
@Table(name = "name_prefix_type")
public class NamePrefixType extends BaseDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".NamePrefixType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = 6269713810593239875L;

}
