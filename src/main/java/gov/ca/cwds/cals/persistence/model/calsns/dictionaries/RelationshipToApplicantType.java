package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = RelationshipToApplicantType.NAMED_QUERY_FIND_ALL, query = "FROM RelationshipToApplicantType ORDER BY id ASC")
@Entity
@Table(name = "relationship_to_applicant_type")
public class RelationshipToApplicantType extends BaseDictionary {

  private static final long serialVersionUID = -4887203144793399786L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".RelationshipToApplicantType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}
