package gov.ca.cwds.cals.service.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public abstract class BaseDTO {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
