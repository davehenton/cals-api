package gov.ca.cwds.cals.health;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.db.DataSourceFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NsDataSourceHealthCheck extends HealthCheck {
    private final DataSourceFactory dataSourceFactory;

    public NsDataSourceHealthCheck(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    protected Result check() throws Exception {
        Connection connection = null;
        String url = dataSourceFactory.getUrl();
        String user = dataSourceFactory.getUser();
        String password = dataSourceFactory.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return HealthCheck.Result.unhealthy(e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return Result.healthy();
    }
}