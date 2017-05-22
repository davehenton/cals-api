package org.hibernate.dialect;

import org.hibernate.cfg.Environment;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.PositionSubstringFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

/**
 * An SQL dialect for CONNX
 * @author Larry McGhaw, CWDS CALS API Team
 */
public class CONNXDialect extends Dialect {

    public CONNXDialect() {
        super();
        registerColumnType( Types.BIT, "bool" );
        registerColumnType( Types.BIGINT, "bigint" );
        registerColumnType( Types.SMALLINT, "smallint" );
        registerColumnType( Types.TINYINT, "tinyint" );
        registerColumnType( Types.INTEGER, "int" );
        registerColumnType( Types.CHAR, "char(l)" );
        registerColumnType( Types.VARCHAR, "varchar($l)" );
        registerColumnType( Types.FLOAT, "float" );
        registerColumnType( Types.DOUBLE, "double" );
        registerColumnType( Types.DATE, "date" );
        registerColumnType( Types.TIME, "time" );
        registerColumnType( Types.TIMESTAMP, "timestamp" );
        registerColumnType( Types.VARBINARY, "varbinary" );
        registerColumnType( Types.CLOB, "longvarchar" );
        registerColumnType( Types.BLOB, "longvarbinary" );
        registerColumnType( Types.NUMERIC, "numeric($p, $s)" );
        registerColumnType( Types.DECIMAL, "decimal($p, $s)" );

        registerFunction( "abs", new StandardSQLFunction("abs") );
        registerFunction( "sign", new StandardSQLFunction("sign", StandardBasicTypes.INTEGER) );

        registerFunction( "acos", new StandardSQLFunction("acos", StandardBasicTypes.DOUBLE) );
        registerFunction( "asin", new StandardSQLFunction("asin", StandardBasicTypes.DOUBLE) );
        registerFunction( "atan", new StandardSQLFunction("atan", StandardBasicTypes.DOUBLE) );
        registerFunction( "cos", new StandardSQLFunction("cos", StandardBasicTypes.DOUBLE) );
        registerFunction( "cot", new StandardSQLFunction("cot", StandardBasicTypes.DOUBLE) );
        registerFunction( "exp", new StandardSQLFunction("exp", StandardBasicTypes.DOUBLE) );
        registerFunction( "ln", new StandardSQLFunction("ln", StandardBasicTypes.DOUBLE) );
        registerFunction( "log", new StandardSQLFunction("log", StandardBasicTypes.DOUBLE) );
        registerFunction( "sin", new StandardSQLFunction("sin", StandardBasicTypes.DOUBLE) );
        registerFunction( "sqrt", new StandardSQLFunction("sqrt", StandardBasicTypes.DOUBLE) );
        registerFunction( "tan", new StandardSQLFunction("tan", StandardBasicTypes.DOUBLE) );
        registerFunction( "radians", new StandardSQLFunction("radians", StandardBasicTypes.DOUBLE) );
        registerFunction( "degrees", new StandardSQLFunction("degrees", StandardBasicTypes.DOUBLE) );

        registerFunction( "stddev", new StandardSQLFunction("stddev", StandardBasicTypes.DOUBLE) );
        registerFunction( "variance", new StandardSQLFunction("variance", StandardBasicTypes.DOUBLE) );

        registerFunction( "rand", new NoArgSQLFunction("rand", StandardBasicTypes.DOUBLE) );

        registerFunction( "round", new StandardSQLFunction("round") );
        registerFunction( "trunc", new StandardSQLFunction("trunc") );
        registerFunction( "ceil", new StandardSQLFunction("ceil") );
        registerFunction( "floor", new StandardSQLFunction("floor") );

        registerFunction( "chr", new StandardSQLFunction("chr", StandardBasicTypes.CHARACTER) );
        registerFunction( "lower", new StandardSQLFunction("lower") );
        registerFunction( "upper", new StandardSQLFunction("upper") );
        registerFunction( "substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING) );
        registerFunction( "to_ascii", new StandardSQLFunction("to_ascii") );
        registerFunction( "ascii", new StandardSQLFunction("ascii", StandardBasicTypes.INTEGER) );
        registerFunction( "length", new StandardSQLFunction("length", StandardBasicTypes.LONG) );
        registerFunction( "char_length", new StandardSQLFunction("char_length", StandardBasicTypes.LONG) );
        registerFunction( "bit_length", new StandardSQLFunction("bit_length", StandardBasicTypes.LONG) );
        registerFunction( "octet_length", new StandardSQLFunction("octet_length", StandardBasicTypes.LONG) );

        registerFunction( "current_date", new NoArgSQLFunction("current_date", StandardBasicTypes.DATE, false) );
        registerFunction( "current_time", new NoArgSQLFunction("current_time", StandardBasicTypes.TIME, false) );
        registerFunction( "current_timestamp", new NoArgSQLFunction("current_timestamp", StandardBasicTypes.TIMESTAMP, false) );
        registerFunction( "now", new NoArgSQLFunction("now", StandardBasicTypes.TIMESTAMP) );

        registerFunction( "user", new NoArgSQLFunction("user", StandardBasicTypes.STRING, false) );
        registerFunction( "current_schema", new NoArgSQLFunction("current_schema", StandardBasicTypes.STRING, true) );

        registerFunction( "concat", new VarArgsSQLFunction( StandardBasicTypes.STRING, "(","||",")" ) );

        registerFunction( "locate", new PositionSubstringFunction() );


        getDefaultProperties().setProperty(Environment.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE);
    }

    public boolean dropConstraints() {
        return false;
    }

    public boolean supportsSequences() {
        return false;
    }

    /**
     * Does this <tt>Dialect</tt> have some kind of <tt>LIMIT</tt> syntax?
     */
    public boolean supportsLimit() {
        return true;
    }

    /**
     * Does this dialect support an offset?
     */
    public boolean supportsLimitOffset() {
        return true;
    }

    /* Does limit support bind variables */
    public boolean supportsVariableLimit() {
        return false;
    }

    public String  getLimitString(String  querySelect, int offset, int limit) {
        return new StringBuffer ( querySelect.length()+22 )
                .append(querySelect)
                .append( "{maxrows " + limit + "," + (offset+1) + "}")
                .toString();
    }
    public boolean bindLimitParametersInReverseOrder() {
        return false;
    }
    public String getIdentitySelectString() {
        return "select @@IDENTITY";
    }
    public boolean supportsIdentityColumns() {
        return true;
    }

    public String  getForUpdateString(String  aliases) {
        return getForUpdateString() + " of " + aliases;
    }


    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    public String  getNoColumnsInsertString() {
        return "default values";
    }

    public Class  getNativeIdentifierGeneratorClass() {
        return SequenceGenerator.class;
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public boolean useInputStreamToInsertBlob() {
        return false;
    }

    public boolean supportsUnionAll() {
        return true;
    }

    public boolean supportsCommentOn() {
        return true;
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

    public String  getTemporaryTableCreationCommand() {
        return "create temp table";
    }

    public String  getTemporaryTableCreationPostfix() {
        return "";
    }

}
