package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CalsApiConfiguration extends BaseApiConfiguration {

    private DataSourceFactory fasDataSourceFactory;

    private DataSourceFactory connxDataSourceFactory;

    @JsonProperty
    public DataSourceFactory getFasDataSourceFactory() {
        return fasDataSourceFactory;
    }

    @JsonProperty
    public void setFasDataSourceFactory(DataSourceFactory fasDataSourceFactory) {
        this.fasDataSourceFactory = fasDataSourceFactory;
    }

    @JsonProperty
    public DataSourceFactory getConnxDataSourceFactory() {
        return connxDataSourceFactory;
    }

    @JsonProperty
    public void setConnxDataSourceFactory(DataSourceFactory connxDataSourceFactory) {
        this.connxDataSourceFactory = connxDataSourceFactory;
    }
}
