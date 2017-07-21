package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = SuffixType.NAMED_QUERY_FIND_ALL, query = "FROM SuffixType ORDER BY value ASC")
@Entity
@Table(name = "suffix_type")
public class SuffixType extends BaseDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".SuffixType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = 7668828600143443981L;

}
