package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author CWDS CALS API Team
 */

@JsonIgnoreProperties({"messages"})
public abstract class BaseDTO {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
