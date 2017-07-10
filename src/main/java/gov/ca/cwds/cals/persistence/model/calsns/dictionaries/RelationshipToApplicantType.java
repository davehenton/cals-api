package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = RelationshipToApplicantType.NAMED_QUERY_FIND_ALL, query = "FROM RelationshipToApplicantType ORDER BY id ASC")
@Entity
@Table(name = "relationship_to_applicant_type")
public class RelationshipToApplicantType extends BaseDictionary {

  private static final long serialVersionUID = 1L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".RelationshipToApplicantType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}