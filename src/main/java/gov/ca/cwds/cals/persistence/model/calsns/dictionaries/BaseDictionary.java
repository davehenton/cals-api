package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** @author CWDS CALS API Team */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@Cacheable
public abstract class BaseDictionary extends BaseDTO implements PersistentObject, Request {

  private static final long serialVersionUID = 1405907682848102125L;

  static final String NAMED_QUERY_PREFIX = "gov.ca.cwds.cals.persistence.model.calsns.dictionaries";
  static final String NAMED_QUERY_FIND_ALL_SUFFIX = ".find.all";

  @ApiModelProperty()
  @NotNull
  @Size(max = 100)
  @Column(name = "description", length = 100, nullable = false)
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public static String buildFindAllQueryName(Class<? extends BaseDictionary> dictionaryClass) {
    return dictionaryClass.getName() + NAMED_QUERY_FIND_ALL_SUFFIX;
  }

  @Override
  public String toString() {
    return String
        .format(getClass().getSimpleName() + "{value = %s})", getValue());
  }

}
