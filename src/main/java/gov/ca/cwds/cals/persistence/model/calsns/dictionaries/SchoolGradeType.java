package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = SchoolGradeType.NAMED_QUERY_FIND_ALL, query = "FROM SchoolGradeType ORDER BY id ASC")
@Entity
@Table(name = "school_grade_type")
public class SchoolGradeType extends LegacyComplaintDictionary {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".SchoolGradeType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  private static final long serialVersionUID = 7524935591532898930L;

}



