package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import static gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType.NAMED_QUERY_FIND_ALL;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/** @author CWDS CALS API Team */
@NamedQuery(name = NAMED_QUERY_FIND_ALL, query = "FROM EducationLevelType ORDER BY id ASC")
@Entity
@Table(name = "education_level_type")
public class EducationLevelType extends BaseDictionary {
  private static final long serialVersionUID = -1861709811537318045L;

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".EducationLevelType" + NAMED_QUERY_FIND_ALL_SUFFIX;
}
