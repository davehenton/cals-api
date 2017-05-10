package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CalsApiConfiguration extends BaseApiConfiguration {

    private DataSourceFactory fasDataSourceFactory;

    @JsonProperty
    public DataSourceFactory getFasDataSourceFactory() {
        return fasDataSourceFactory;
    }

    @JsonProperty
    public void setFasDataSourceFactory(DataSourceFactory fasDataSourceFactory) {
        this.fasDataSourceFactory = fasDataSourceFactory;
    }
}
