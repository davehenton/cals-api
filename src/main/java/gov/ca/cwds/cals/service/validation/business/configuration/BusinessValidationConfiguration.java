package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;

/**
 * @author CWDS CALS API Team
 */

public interface BusinessValidationConfiguration<T extends BaseDTO> extends
    CalsSessionFactoryAware {

  String getAgendaGroup();

  BusinessValidationParameterObject<T> getBusinessValidationParameterObject(Object[] parameters);

  RFA1aForm buildModifiedForm(RFA1aForm form, T entityDTO);

}
