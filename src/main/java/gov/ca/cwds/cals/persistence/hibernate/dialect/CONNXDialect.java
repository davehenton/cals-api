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
 * @author CWDS CALS API Team
 */
public class CONNXDialect extends Dialect {

  public CONNXDialect() {
    super();
    registerColumnType(Types.BIT, "bool");
    registerColumnType(Types.BIGINT, "bigint");
    registerColumnType(Types.SMALLINT, "smallint");
    registerColumnType(Types.TINYINT, "tinyint");
    registerColumnType(Types.INTEGER, "int");
    registerColumnType(Types.CHAR, "char(l)");
    registerColumnType(Types.VARCHAR, "varchar($l)");
    registerColumnType(Types.FLOAT, "float");
    registerColumnType(Types.DOUBLE, "double");
    registerColumnType(Types.DATE, "date");
    registerColumnType(Types.TIME, "time");
    registerColumnType(Types.TIMESTAMP, "timestamp");
    registerColumnType(Types.VARBINARY, "varbinary");
    registerColumnType(Types.CLOB, "longvarchar");
    registerColumnType(Types.BLOB, "longvarbinary");
    registerColumnType(Types.NUMERIC, "numeric($p, $s)");
    registerColumnType(Types.DECIMAL, "decimal($p, $s)");

    registerFunction("abs", new StandardSQLFunction("abs"));
    registerFunction("sign", new StandardSQLFunction("sign", INTEGER));

    registerFunction("acos", new StandardSQLFunction("acos", DOUBLE));
    registerFunction("asin", new StandardSQLFunction("asin", DOUBLE));
    registerFunction("atan", new StandardSQLFunction("atan", DOUBLE));
    registerFunction("cos", new StandardSQLFunction("cos", DOUBLE));
    registerFunction("cot", new StandardSQLFunction("cot", DOUBLE));
    registerFunction("exp", new StandardSQLFunction("exp", DOUBLE));
    registerFunction("ln", new StandardSQLFunction("ln", DOUBLE));
    registerFunction("log", new StandardSQLFunction("log", DOUBLE));
    registerFunction("sin", new StandardSQLFunction("sin", DOUBLE));
    registerFunction("sqrt", new StandardSQLFunction("sqrt", DOUBLE));
    registerFunction("tan", new StandardSQLFunction("tan", DOUBLE));
    registerFunction("radians", new StandardSQLFunction("radians", DOUBLE));
    registerFunction("degrees", new StandardSQLFunction("degrees", DOUBLE));

    registerFunction("stddev", new StandardSQLFunction("stddev", DOUBLE));
    registerFunction("variance", new StandardSQLFunction("variance", DOUBLE));

    registerFunction("rand", new NoArgSQLFunction("rand", DOUBLE));

    registerFunction("round", new StandardSQLFunction("round"));
    registerFunction("trunc", new StandardSQLFunction("trunc"));
    registerFunction("ceil", new StandardSQLFunction("ceil"));
    registerFunction("floor", new StandardSQLFunction("floor"));

    registerFunction("chr", new StandardSQLFunction("chr", CHARACTER));
    registerFunction("lower", new StandardSQLFunction("lower"));
    registerFunction("upper", new StandardSQLFunction("upper"));
    registerFunction("substr", new StandardSQLFunction("substr", STRING));
    registerFunction("to_ascii", new StandardSQLFunction("to_ascii"));
    registerFunction("ascii", new StandardSQLFunction("ascii", INTEGER));
    registerFunction("length", new StandardSQLFunction("length", LONG));
    registerFunction("char_length", new StandardSQLFunction("char_length", LONG));
    registerFunction("bit_length", new StandardSQLFunction("bit_length", LONG));
    registerFunction("octet_length", new StandardSQLFunction("octet_length", LONG));

    registerFunction("current_date", new NoArgSQLFunction("current_date", DATE, false));
    registerFunction("current_time", new NoArgSQLFunction("current_time", TIME, false));
    registerFunction("current_timestamp",
        new NoArgSQLFunction("current_timestamp", TIMESTAMP, false));
    registerFunction("now", new NoArgSQLFunction("now", TIMESTAMP));

    registerFunction("user", new NoArgSQLFunction("user", STRING, false));
    registerFunction("current_schema", new NoArgSQLFunction("current_schema", STRING, true));

    registerFunction("concat", new VarArgsSQLFunction(STRING, "(", "||", ")"));

    registerFunction("locate", new PositionSubstringFunction());

    getDefaultProperties().setProperty(Environment.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE);
  }

  @Override
  public boolean dropConstraints() {
    return false;
  }

  @Override
  public boolean supportsSequences() {
    return false;
  }

  /**
   * Does this <tt>Dialect</tt> have some kind of <tt>LIMIT</tt> syntax?
   */
  @Override
  public boolean supportsLimit() {
    return true;
  }

  /**
   * Does this dialect support an offset?
   */
  @Override
  public boolean supportsLimitOffset() {
    return true;
  }

  /* Does limit support bind variables */
  @Override
  public boolean supportsVariableLimit() {
    return false;
  }

  @Override
  public String getLimitString(String querySelect, int offset, int limit) {
    return new StringBuffer(querySelect.length() + 22)
        .append(querySelect)
        .append("{maxrows ").append(limit).append(",").append(offset + 1).append("}")
        .toString();
  }

  @Override
  public boolean bindLimitParametersInReverseOrder() {
    return false;
  }

  public String getIdentitySelectString() {
    return "select @@IDENTITY";
  }

  public boolean supportsIdentityColumns() {
    return true;
  }

  @Override
  public String getForUpdateString(String aliases) {
    return getForUpdateString() + " of " + aliases;
  }


  public boolean hasDataTypeInIdentityColumn() {
    return false;
  }

  @Override
  public String getNoColumnsInsertString() {
    return "default values";
  }

  @Override
  public Class getNativeIdentifierGeneratorClass() {
    return SequenceGenerator.class;
  }

  @Override
  public boolean supportsOuterJoinForUpdate() {
    return false;
  }

  @Override
  public boolean useInputStreamToInsertBlob() {
    return false;
  }

  @Override
  public boolean supportsUnionAll() {
    return true;
  }

  @Override
  public boolean supportsCommentOn() {
    return true;
  }

  public boolean supportsTemporaryTables() {
    return true;
  }

  public String getTemporaryTableCreationCommand() {
    return "create temp table";
  }

  public String getTemporaryTableCreationPostfix() {
    return "";
  }

}
