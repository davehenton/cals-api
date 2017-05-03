package gov.ca.cwds.cals.persistence;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    public static void createFasDdl() throws SQLException, ClassNotFoundException, LiquibaseException {
//        Class.forName("org.postgresql.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/", "postgres", "postgres");
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

        ClassLoaderResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
        new Liquibase("gov/ca/cwds/cals/model/fas/liquibase/fas_schema.xml", resourceAccessor, database).update((String) null);
        new Liquibase("gov/ca/cwds/cals/model/fas/liquibase/fas_structure.xml", resourceAccessor, database).update((String) null);
        new Liquibase("gov/ca/cwds/cals/model/fas/liquibase/fas_constraints.xml", resourceAccessor, database).update((String) null);

        connection.close();
    }
}
