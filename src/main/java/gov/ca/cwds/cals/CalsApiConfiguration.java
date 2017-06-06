package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CalsApiConfiguration extends BaseApiConfiguration {

    private DataSourceFactory fasDataSourceFactory;


    private DataSourceFactory lisDataSourceFactory;


    @JsonProperty
    public DataSourceFactory getFasDataSourceFactory() {
        return fasDataSourceFactory;
    }

    @JsonProperty
    public DataSourceFactory getLisDataSourceFactory() {
        return lisDataSourceFactory;
    }

    public void setFasDataSourceFactory(DataSourceFactory fasDataSourceFactory) {
        this.fasDataSourceFactory = fasDataSourceFactory;
    }

    public void setLisDataSourceFactory(DataSourceFactory lisDataSourceFactory) {
        this.lisDataSourceFactory = lisDataSourceFactory;
    }

}
