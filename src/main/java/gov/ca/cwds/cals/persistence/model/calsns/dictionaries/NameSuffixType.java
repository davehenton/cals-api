package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = NameSuffixType.NAMED_QUERY_FIND_ALL, query = "FROM NameSuffixType ORDER BY value ASC")
@Entity
@Cacheable
@Table(name = "name_suffix_type")
public class NameSuffixType extends BaseDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".NameSuffixType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = 7668828600143443981L;

}
