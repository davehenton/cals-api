package gov.ca.cwds.cals.service.mapper.rfa;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.HyperlinkDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team.
 */
@Mapper(imports = {Constants.API.class, HyperlinkDTO.class})
public interface RFA1aFormMapper {

  @Mapping(target = "initialApplication", source = "application.initialApplication")
  @Mapping(target = "otherType", source = "application.otherType")
  @Mapping(target = "otherTypeDescription", source = "application.otherTypeDescription")
  @Mapping(target = "applicationCounty", source = "application.applicationCounty")
  @Mapping(target = "residence", expression = "java(new HyperlinkDTO(String.format(RFA_1A_FORMS + \"/{%s}/\" + RESIDENCE, form.getId())))")
  RFA1aFormDTO toRFA1aFormDTO(RFA1aForm form);
}
