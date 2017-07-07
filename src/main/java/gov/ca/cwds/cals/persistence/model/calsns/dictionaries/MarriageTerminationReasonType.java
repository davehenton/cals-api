package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team.
 */
@NamedQuery(
    name = MarriageTerminationReasonType.NAMED_QUERY_FIND_ALL,
    query = "FROM MarriageTerminationReasonType ORDER BY id ASC"
)
@Entity
@Table(name = "marriage_termination_reason")
public class MarriageTerminationReasonType extends BaseDictionary {

  private static final long serialVersionUID = 1351466432617639006L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".MarriageTerminationReasonType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}
