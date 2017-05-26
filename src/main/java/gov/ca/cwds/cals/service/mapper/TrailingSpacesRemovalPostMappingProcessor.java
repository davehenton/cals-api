package gov.ca.cwds.cals.service.mapper;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public class TrailingSpacesRemovalPostMappingProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(TrailingSpacesRemovalPostMappingProcessor.class);
    private static final String ERROR_MESSAGE = "Do not apply RemoveTrailingSpaces annotation for non-string field";

    @AfterMapping
    protected void removeTrailingSpaces(@MappingTarget Object object) {
        for (Field field: FieldUtils.getFieldsWithAnnotation(object.getClass(), RemoveTrailingSpaces.class)) {
            try {
                PropertyUtils.setProperty(object, field.getName(),
                        StringUtils.trim((String) PropertyUtils.getProperty(object, field.getName())));
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                LOG.error(ERROR_MESSAGE, e);
                throw new IllegalStateException(e);
            }
        }
    }

}
