package gov.ca.cwds.cals.persistence.hibernate.dialect;

import static org.hibernate.type.StandardBasicTypes.CHARACTER;
import static org.hibernate.type.StandardBasicTypes.DATE;
import static org.hibernate.type.StandardBasicTypes.DOUBLE;
import static org.hibernate.type.StandardBasicTypes.INTEGER;
import static org.hibernate.type.StandardBasicTypes.LONG;
import static org.hibernate.type.StandardBasicTypes.STRING;
import static org.hibernate.type.StandardBasicTypes.TIME;
import static org.hibernate.type.StandardBasicTypes.TIMESTAMP;

import java.sql.Types;
import javax.persistence.SequenceGenerator;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.PositionSubstringFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;

/**
 * CONNX 12 version Hibernate dialect.
 * @author CWDS CALS API Team
 */
public class CONNX12Dialect extends CONNXDialect {

  @Override
  public String getLimitString(String querySelect, int offset, int limit) {
    return new StringBuffer(querySelect.length() + 22)
        .append(querySelect)
        .append("{maxrows ").append(limit).append(",").append(offset + 1).append("}")
        .toString();
  }

}
