package gov.ca.cwds.cals.health;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.db.DataSourceFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceHealthCheck extends HealthCheck {
    private final DataSourceFactory dataSourceFactory;

    public DataSourceHealthCheck(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    protected Result check() throws Exception {
        String url = dataSourceFactory.getUrl();
        String user = dataSourceFactory.getUser();
        String password = dataSourceFactory.getPassword();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            return Result.healthy();
        } catch (SQLException e) {
            return HealthCheck.Result.unhealthy(e.getMessage());
        }

    }
}