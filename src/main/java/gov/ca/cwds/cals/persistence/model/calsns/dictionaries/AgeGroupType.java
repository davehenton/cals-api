package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import static gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType.NAMED_QUERY_FIND_ALL;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/** @author CWDS CALS API Team */
@NamedQuery(name = NAMED_QUERY_FIND_ALL, query = "FROM AgeGroupType ORDER BY id ASC")
@Entity
@Table(name = "age_group_type")
public class AgeGroupType extends BaseDictionary {
  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".AgeGroupType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = -3799114345234018083L;
}