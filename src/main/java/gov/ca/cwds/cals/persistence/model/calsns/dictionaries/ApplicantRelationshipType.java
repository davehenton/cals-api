package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team.
 */
@NamedQuery(
    name = ApplicantRelationshipType.NAMED_QUERY_FIND_ALL,
    query = "FROM ApplicantRelationshipType ORDER BY id ASC"
)
@Entity
@Table(name = "applicant_relationship_type")
public class ApplicantRelationshipType extends BaseDictionary {

  private static final long serialVersionUID = -129674356484145734L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".ApplicantRelationshipType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}