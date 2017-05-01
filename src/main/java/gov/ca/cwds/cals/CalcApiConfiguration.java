package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.annotation.Nullable;

public class CalcApiConfiguration extends BaseApiConfiguration {

    @Nullable
    private DataSourceFactory lisDataSourceFactory;

    @JsonProperty
    public DataSourceFactory getLisDataSourceFactory() {
        return lisDataSourceFactory;
    }

    @JsonProperty
    public void setLisDataSourceFactory(@Nullable DataSourceFactory lisDataSourceFactory) {
        this.lisDataSourceFactory = lisDataSourceFactory;
    }
}
